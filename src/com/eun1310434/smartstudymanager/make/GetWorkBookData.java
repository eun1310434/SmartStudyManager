package com.eun1310434.smartstudymanager.make;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eun1310434.smartstudymanager.db.SSM_SERVER_DB;

public class GetWorkBookData {

	public String check = "";
	private String[] InfoData = {
			"", // 0 getSubject
	};
	
	public ArrayList<QuestionInfo_AL> QuestionDataList 	= null; // ��ü����

	
	/** GetData() */
	private Workbook wbGet;
	public GetWorkBookData(String _db_path) throws IOException {

		File GetData_file = new File(_db_path);
		FileInputStream GetData_fis = new FileInputStream(GetData_file);
		wbGet = new XSSFWorkbook(GetData_fis);

		if (wbGet != null) {// �����ͷ� �ٲ������ Ȯ��
			setInfoData(); // ���� ����
			//setQuestionDataList_mysql();
			setQuestionDataList_excel(); // ��ü���� ����
			setArrangeQuestionDataList(QuestionDataList);
		}
	}

	private void setInfoData() {
		check = "";

		Sheet sheet = wbGet.getSheet("printInfo");// printDB�̶�� ��Ʈ�� �ҷ���

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			for (int i = 0; i < InfoData.length; i++) {
				rows.hasNext();
				Iterator<Cell> cells = rows.next().iterator(); // ��
				InfoData[i] = cells.next().getStringCellValue();
			}
		}
	}

	private void setQuestionDataList_mysql() { // �������� ����
		SSM_SERVER_DB d = new SSM_SERVER_DB();
		ArrayList<QuestionInfo_AL> a = d.Question_DATA_SET("����","����", "��2", "1�б�", "1��");
		d.quit();
		
	}

	private void setQuestionDataList_excel() { // �������� ����
		check = "";
		QuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printQuestionDataList");// printQuestionDataList�̶��
																	// ��Ʈ�� �ҷ���,
																	// ��Īö��!

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell�� �����͵���
																// ���ɴϴ�.
				check = cells.next().getStringCellValue();

				if (check.equals("-")) {
					break;
				}

				QuestionDataList.add(new QuestionInfo_AL(
						"", 								// �򰡳�¥_classDate
						
						check, 								// �������� ���
					 										// _questionPath
						cells.next().getStringCellValue(), 	// �������� ��� -
															// _solutionPath
						cells.next().getStringCellValue(), 	// �������� -
															// _workBook_name
						cells.next().getStringCellValue(), 	// ���������� -
															// _questionPage
						cells.next().getStringCellValue(), 	// ������ȣ -
															// _questionNum
						cells.next().getStringCellValue(), 	// �������� -
															// _questionLevel
						cells.next().getStringCellValue(), 	// �����ܿ� -
															// _questionUnit
						cells.next().getStringCellValue(), 	// �������� -
															// _questionType
						"-", 								// �ֱ��н��� -
															// _recentStudyDate
						"-", 								// �ֱ��н���� -
															// _recentStudyResultCheck
						"-", 								// ����� -
															// _correctPercent
						"-", 								// ����Ƚ�� -
															// _correctCount
						"-"));								// ����Ƚ�� -
															// _incorrectCount
			}
		}
	}
	
	/** getUnitArrangeQuestionDataList */
	public void  setArrangeQuestionDataList(ArrayList<QuestionInfo_AL> ArrangeQuestionDataList) {
        // �ܿ��� ����
		QuestionInfo_UNIT_SORT level_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, level_sort);
        
		QuestionInfo_UNIT_SORT unit_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, unit_sort);
	}
	
	
	
	
	/** InfoData */
	public String getSubject() {
		return InfoData[0];
	}

}
