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
	
	public ArrayList<QuestionInfo_AL> QuestionDataList 	= null; // 전체문제

	
	/** GetData() */
	private Workbook wbGet;
	public GetWorkBookData(String _db_path) throws IOException {

		File GetData_file = new File(_db_path);
		FileInputStream GetData_fis = new FileInputStream(GetData_file);
		wbGet = new XSSFWorkbook(GetData_fis);

		if (wbGet != null) {// 데이터로 바뀌었는지 확인
			setInfoData(); // 정보 셋팅
			//setQuestionDataList_mysql();
			setQuestionDataList_excel(); // 전체문제 셋팅
			setArrangeQuestionDataList(QuestionDataList);
		}
	}

	private void setInfoData() {
		check = "";

		Sheet sheet = wbGet.getSheet("printInfo");// printDB이라는 시트를 불러옴

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			for (int i = 0; i < InfoData.length; i++) {
				rows.hasNext();
				Iterator<Cell> cells = rows.next().iterator(); // 열
				InfoData[i] = cells.next().getStringCellValue();
			}
		}
	}

	private void setQuestionDataList_mysql() { // 진도문제 셋팅
		SSM_SERVER_DB d = new SSM_SERVER_DB();
		ArrayList<QuestionInfo_AL> a = d.Question_DATA_SET("고도현","수학", "중2", "1학기", "1차");
		d.quit();
		
	}

	private void setQuestionDataList_excel() { // 진도문제 셋팅
		check = "";
		QuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printQuestionDataList");// printQuestionDataList이라는
																	// 시트를 불러옴,
																	// 명칭철저!

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell의 데이터들을
																// 들고옵니다.
				check = cells.next().getStringCellValue();

				if (check.equals("-")) {
					break;
				}

				QuestionDataList.add(new QuestionInfo_AL(
						"", 								// 평가날짜_classDate
						
						check, 								// 문제파일 경로
					 										// _questionPath
						cells.next().getStringCellValue(), 	// 답지파일 경로 -
															// _solutionPath
						cells.next().getStringCellValue(), 	// 교재제목 -
															// _workBook_name
						cells.next().getStringCellValue(), 	// 문제페이지 -
															// _questionPage
						cells.next().getStringCellValue(), 	// 문제번호 -
															// _questionNum
						cells.next().getStringCellValue(), 	// 문제레벨 -
															// _questionLevel
						cells.next().getStringCellValue(), 	// 문제단원 -
															// _questionUnit
						cells.next().getStringCellValue(), 	// 문제구분 -
															// _questionType
						"-", 								// 최근학습일 -
															// _recentStudyDate
						"-", 								// 최근학습결과 -
															// _recentStudyResultCheck
						"-", 								// 정답률 -
															// _correctPercent
						"-", 								// 정답횟수 -
															// _correctCount
						"-"));								// 오답횟수 -
															// _incorrectCount
			}
		}
	}
	
	/** getUnitArrangeQuestionDataList */
	public void  setArrangeQuestionDataList(ArrayList<QuestionInfo_AL> ArrangeQuestionDataList) {
        // 단원별 정렬
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
