package com.eun1310434.smartstudymanager.make;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.eun1310434.smartstudymanager.db.SSM_SERVER_DB;

public class GetStudentData {

	public String  StudentName 		=	"";	//�̸�
	public String  StudentSubject 	=	"";	//����	ex) ����,����(����)
	public String  StudentGrade 	=	"";	//�г�	ex) ��1,��2,��3,��1,��2,��3
	public String  StudentSemester 	=	"";	//�б�	ex) 1�б�, 2�б�
	public String  StudentTest 		=	"";	//��	ex) 1��, 2��
	public String  TestDate 		=	"";	//��¥	ex) 17�� 8�� 21�� ������ , 17-08-21, 17-8-21
	
	public String  GradeSemester	=	"";
	public String  ClassName		=	"";
	public String  MyNoteName		=	"";
	

	public String  preditPoint		=	"";
	public String  correctPercent	=	"";	//�����
	public String  progressPercent	=	"";	//������
	public String  studyLevel		=	""; //���̵�
	public String  studyCount		=	""; //�н�Ƚ��
	private ArrayList<QuestionInfo_AL> 	TotalQuestionDataList 	= null; // ��ü����
	private ArrayList<QuestionInfo_AL> 	StudyQuestionDataList 	= null; // �н�����
	private ArrayList<UnitInfo_AL> 		UnitDataList 			= null; // ����
	public ArrayList<StudyInfo_AL> 	StudyDataList 			= null; // ����
	
	/** GetData() */
	private Workbook wbGet;
	public GetStudentData(
			String _db_path,
			String _StudentName,
			String _StudentSubject,
			String _StudentGrade,
			String _StudentSemester,
			String _StudentTest,
			String _TestDate
			) throws IOException {

		File GetData_file 			= new File(_db_path);
		FileInputStream GetData_fis = new FileInputStream(GetData_file);
		wbGet 						= new XSSFWorkbook(GetData_fis);

		StudentName 		=	_StudentName;		//�̸�
		StudentSubject 		=	_StudentSubject;	//����	ex) ����,����(����)
		StudentGrade 		=	_StudentGrade;		//�г�	ex) ��1,��2,��3,��1,��2,��3
		StudentSemester 	=	_StudentSemester;	//�б�	ex) 1�б�, 2�б�
		StudentTest 		=	_StudentTest;		//��	ex) 1��, 2��
		TestDate 			=	_TestDate;			//��¥	ex) 17�� 8�� 21�� ������ , 17-08-21, 17-8-21
		GradeSemester		=	StudentGrade + " - " + StudentSemester + " - " + StudentTest;
		ClassName			=	"[SSM] " + StudentName +"_"+StudentSubject+"_"+StudentGrade+"_"+StudentSemester+"_"+StudentTest+ "(" + TestDate+")" ;
		MyNoteName			=	StudentGrade+"_"+StudentSemester+"_"+StudentTest;
		

		if (wbGet != null) {
			setInfoData();
			//setQuestionDataList_mysql();
			setStudyQuestionDataList_excel();	// �н����� ����
			setTotalQuestionDataList_excel(); 	// ��ü���� ����
			setUnitDataList_excel();			// �ܿ��� ����
			setStudyDataList_excel();			// �ܿ��� ����
		}
	}

	private void setInfoData() {
		Sheet sheet = wbGet.getSheet("printInfo");// printDB�̶�� ��Ʈ�� �ҷ���

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.
			
			Iterator<Cell> preditPoint_cells = rows.next().iterator();
			preditPoint = Integer.parseInt(preditPoint_cells.next().getStringCellValue())+"";

			Iterator<Cell> correctPercent_cells = rows.next().iterator();
			correctPercent = (int) Double.parseDouble(correctPercent_cells.next().getStringCellValue())+"";

			Iterator<Cell> progressPercent_cells = rows.next().iterator();
			progressPercent = (int) Double.parseDouble(progressPercent_cells.next().getStringCellValue())+"";

			Iterator<Cell> studyLevel_cells = rows.next().iterator();
			studyLevel = (int) Double.parseDouble(studyLevel_cells.next().getStringCellValue())+"";

			Iterator<Cell> studyCount_cells = rows.next().iterator();
			studyCount = (int) Double.parseDouble(studyCount_cells.next().getStringCellValue())+"";
			
		}
		/*
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		System.out.println(preditPoint);
		System.out.println(correctPercent);
		System.out.println(progressPercent);
		System.out.println(studyLevel);
		System.out.println(studyCount);
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		*/
	}

	private void setQuestionDataList_mysql() { // �������� ����
		SSM_SERVER_DB d = new SSM_SERVER_DB();
		StudyQuestionDataList = d.Question_DATA_SET(StudentName,StudentSubject, StudentGrade, StudentSemester, StudentTest);
		d.quit();
	}
	
	private void setTotalQuestionDataList_excel() { // �������� ����
		String questionPath = "";
		TotalQuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printTotalQuestionDataList");// printTotalQuestionDataList�̶��
																	// ��Ʈ�� �ҷ���,
																	// ��Īö��!

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell�� �����͵���
																// ���ɴϴ�.
				questionPath = cells.next().getStringCellValue();

				if (questionPath.equals("-")) {
					break;
				}

				TotalQuestionDataList.add(new QuestionInfo_AL(
						TestDate, 							// �򰡳�¥
															// _classDate
						questionPath, 						// �������� ���
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
						cells.next().getStringCellValue(), 	// �ֱ��н��� -
															// _recentStudyDate
						cells.next().getStringCellValue(), 	// �ֱ��н���� -
															// _recentStudyResultCheck
						cells.next().getStringCellValue(), 	// ����� -
															// _correctPercent
						cells.next().getStringCellValue(), 	// ����Ƚ�� -
															// _correctCount
						cells.next().getStringCellValue()));// ����Ƚ�� -
															// _incorrectCount
			}
		}
		setArrangeQuestionDataList(TotalQuestionDataList);
	}

	private void setStudyQuestionDataList_excel() { // �н����� ����
		String questionPath = "";
		StudyQuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudyQuestionDataList");// printStudyQuestionDataList�̶��
																	// ��Ʈ�� �ҷ���,
																	// ��Īö��!

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell�� �����͵���
																// ���ɴϴ�.
				questionPath = cells.next().getStringCellValue();

				if (questionPath.equals("-")) {
					break;
				}

				StudyQuestionDataList.add(new QuestionInfo_AL(
						TestDate, 							// �򰡳�¥
					 										// _classDate
						questionPath, 						// �������� ���
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
						cells.next().getStringCellValue(), 	// �ֱ��н��� -
															// _recentStudyDate
						cells.next().getStringCellValue(), 	// �ֱ��н���� -
															// _recentStudyResultCheck
						cells.next().getStringCellValue(), 	// ����� -
															// _correctPercent
						cells.next().getStringCellValue(), 	// ����Ƚ�� -
															// _correctCount
						cells.next().getStringCellValue()));// ����Ƚ�� -
															// _incorrectCount
			}
		}
		setArrangeQuestionDataList(StudyQuestionDataList);
	}

	/** setArrangeQuestionDataList */
	public void  setArrangeQuestionDataList(ArrayList<QuestionInfo_AL> ArrangeQuestionDataList) {
        // �ܿ��� ����
		QuestionInfo_UNIT_SORT level_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, level_sort);
        
		QuestionInfo_UNIT_SORT unit_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, unit_sort);
	}
	

	
	private void setUnitDataList_excel()  {  // �н����� ����
		String unitSN = "";
		UnitDataList = new ArrayList<UnitInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudyUnitDataList");// printStudyUnitDataList�̶��
																// ��Ʈ�� �ҷ���,
																// ��Īö��!

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell�� �����͵���
																// ���ɴϴ�.
				unitSN = cells.next().getStringCellValue();

				if (unitSN.equals("-")) {
					break;
				}

				UnitDataList.add(new UnitInfo_AL(
						StudentGrade,
						StudentName,
						StudentSemester,
						StudentTest,
						TestDate, 								// �򰡳�¥
					 											// _classDate
						unitSN, 								// �ܿ�����
														 		// _unitSN
						cells.next().getStringCellValue(), 		// �ֱ��н����
																// _recentStudyResultCheck
						cells.next().getStringCellValue(), 		// �����
																// _correctPercent
						cells.next().getStringCellValue(), 		// �ֱ��н���¥
																// _recentStudyDate
						cells.next().getStringCellValue(), 		// ������
																// _progressPercent
						cells.next().getStringCellValue(), 		// ȹ�泭�̵�
																// _level
						cells.next().getStringCellValue())); 	// ���н�Ƚ��
																// _totalStudyCount
			}
		}
	}

	/** setArrangeUnitDataList_Unit*/
	public void  setArrangeUnitDataList_Unit(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // �ֱ��н� ����
		UnitInfo_UNIT_SORT unit_unit_sort = new UnitInfo_UNIT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_unit_sort);
	}

	/** setArrangeUnitDataList_Correct */
	public void  setArrangeUnitDataList_Correct(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // ����� ����
		UnitInfo_CORRECT_SORT unit_correct_sort = new UnitInfo_CORRECT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_correct_sort);
	}

	/** setArrangeUnitDataList_Recent */
	public void  setArrangeUnitDataList_Recent(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // �ֱ��н� ����
		UnitInfo_RECENT_SORT unit_recent_sort = new UnitInfo_RECENT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_recent_sort);
	}
	
	

	/** �н���� ������ ���� */
	private void setStudyDataList_excel()  {  // �н����� ����
		String questionSN = "";
		StudyDataList = new ArrayList<StudyInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudy");	// printStudy�̶��
													// ��Ʈ�� �ҷ���,��Īö��!

		if (sheet != null) {// �ش� ��Ʈ�� �ִ��� Ȯ��
			Iterator<Row> rows = sheet.rowIterator(); // row�� �����͵��� ���ɴϴ�.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell�� �����͵���  ���ɴϴ�.
				questionSN = cells.next().getStringCellValue();

				if (questionSN.equals("-")) {
					break;
				}

				StudyDataList.add(new StudyInfo_AL(
						StudentName, 							// �л��̸�
					 											// _classDate
						questionSN, 							// ��������
														 		// _questionSN
						cells.next().getStringCellValue(), 		// �н���¥
																// _StudyDate
						cells.next().getStringCellValue())); 	// �н����
																// _StudyResultCheck
			}
		}
	}
	
	
	
	
	
	
	/** getRandomQuestionDataList*/
	private int levelFiveCount;
	private int levelFivePercent;
	private int levelFourCount;
	private int levelFourPercent;
	private int levelThreeCount;
	private int levelThreePercent;
	private int levelTwoCount;
	private int levelTwoPercent;
	private int levelOneCount;
	private int levelOnePercent;
	
	public ArrayList<QuestionInfo_AL>  getRandomQuestionDataList(
			String _Level, 
			int _QuestionCount,  	 			
			ArrayList<QuestionInfo_AL> _QuestionDataList) {

		System.out.println("�н�--------------------------------");
		System.out.println("��ü����    : "+Integer.toString(_QuestionDataList.size()));
		System.out.println("��ճ��̵� : "+getLevel_Avg(_QuestionDataList));
		
		levelFiveCount = Integer.parseInt(getLevelCount(_QuestionDataList,5));
		levelFivePercent = levelFiveCount*100/_QuestionDataList.size();
		System.out.println("��5��       : "+Integer.toString(levelFiveCount)+"��	"+Integer.toString(levelFivePercent)+"%");
		
		levelFourCount = Integer.parseInt(getLevelCount(_QuestionDataList,4));
		levelFourPercent = levelFourCount*100/_QuestionDataList.size();
		System.out.println("��4��       : "+Integer.toString(levelFourCount)+"��	"+Integer.toString(levelFourPercent)+"%");

		levelThreeCount = Integer.parseInt(getLevelCount(_QuestionDataList,3));
		levelThreePercent = levelThreeCount*100/_QuestionDataList.size();
		System.out.println("��3��       : "+Integer.toString(levelThreeCount)+"��	"+Integer.toString(levelThreePercent)+"%");

		levelTwoCount = Integer.parseInt(getLevelCount(_QuestionDataList,2));
		levelTwoPercent = levelTwoCount*100/_QuestionDataList.size();
		System.out.println("��2��       : "+Integer.toString(levelTwoCount)+"��	"+Integer.toString(levelTwoPercent)+"%");
		
		levelOneCount = Integer.parseInt(getLevelCount(_QuestionDataList,1));
		levelOnePercent = levelOneCount*100/_QuestionDataList.size();
		System.out.println("��1��       : "+Integer.toString(levelOneCount)+"��	"+Integer.toString(levelOnePercent)+"%");
		
		
		 ArrayList<QuestionInfo_AL> out = new ArrayList<QuestionInfo_AL>();
		 
		 /**���� ����*/
		 //�ֱ�Ʋ������, ����� ���� ���� , �ѹ��� ��Ǭ����, �н����� �����ȹ���
		 ArrayList<QuestionInfo_AL> Q_not_study = new ArrayList<QuestionInfo_AL>(); // �ѹ��� ��Ǭ ����
		 
		 ArrayList<QuestionInfo_AL> Q_A = new ArrayList<QuestionInfo_AL>(); // ����� 0%
		 
		 ArrayList<QuestionInfo_AL> Q_B = new ArrayList<QuestionInfo_AL>(); // �ֱٿ� Ʋ�� && ����� 50% ����
		 
		 ArrayList<QuestionInfo_AL> Q_C = new ArrayList<QuestionInfo_AL>(); // ����� 40% ����
		 
		 ArrayList<QuestionInfo_AL> Q_D = new ArrayList<QuestionInfo_AL>(); // ����� 50% ���� && �� �н��Ⱓ 2�� �̻�
		 
		 ArrayList<QuestionInfo_AL> Q_E = new ArrayList<QuestionInfo_AL>(); // �� �н��Ⱓ 2�� �̻�

		 ArrayList<QuestionInfo_AL> Q_study = new ArrayList<QuestionInfo_AL>(); // 1ȸ �̻� �н�
		 
		 
		 
		 for(int i = 0 ; i < _QuestionDataList.size(); i++){
			 
			 if(_QuestionDataList.get(i).correctPercent_double == 0.0 && _QuestionDataList.get(i).totalCount_Int > 0){// ����� 0%
				 Q_A.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).recentStudyResultCheck.equals("X") && _QuestionDataList.get(i).correctPercent_double <= 50.0){// �ֱٿ� Ʋ�� && ����� 50% ����
				 Q_B.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).correctPercent_double <= 40.0 && _QuestionDataList.get(i).totalCount_Int > 0){ // ����� 40% ����
				 Q_C.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).correctPercent_double <= 50.0 && _QuestionDataList.get(i).recentStudyPassDate >= 14){ // ����� 50% ���� && �� �н��Ⱓ 2�� �̻�
				 Q_D.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).recentStudyPassDate >= 14 ){ //  �� �н��Ⱓ 2�� �̻�
				 Q_E.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).totalCount_Int >= 1){// 1ȸ �̻� �н�
				 Q_study.add(_QuestionDataList.get(i));
				 
			 }else{// �ѹ��� ��Ǭ ����
				 Q_not_study.add(_QuestionDataList.get(i));
			 }
		 }

		System.out.println(Integer.toString(Q_A.size())+"��"+"- ����� 0%");
		System.out.println(Integer.toString(Q_B.size())+"��"+"- �ֱٿ� Ʋ�� && ����� 50% ����");
		System.out.println(Integer.toString(Q_not_study.size())+"��"+"- �н� ��Ǭ ����");
		System.out.println(Integer.toString(Q_C.size())+"��"+"- ����� 40% ����");
		System.out.println(Integer.toString(Q_D.size())+"��"+"- ����� 50% ���� && �� �н��Ⱓ 2�� �̻�");
		System.out.println(Integer.toString(Q_E.size())+"��"+"- �� �н��Ⱓ 2�� �̻� ");
		System.out.println(Integer.toString(Q_study.size())+"��"+"- �н��� ������ ����");

		/**�����Է�*/
		int Q_A_Count 			= addRandomQuestionDataList(Q_A,out,_QuestionCount,_Level);// ����� 0%
		int Q_B_Count 			= addRandomQuestionDataList(Q_B,out,_QuestionCount,_Level);// �ֱٿ� Ʋ��
		int Q_not_study_count 	= addRandomQuestionDataList(Q_not_study,out,_QuestionCount,_Level);// �ѹ��� ��Ǭ ����
		int Q_C_Count 			= addRandomQuestionDataList(Q_C,out,_QuestionCount,_Level);// ����� 40% ����
		int Q_D_Count 			= addRandomQuestionDataList(Q_D,out,_QuestionCount,_Level);// ����� 50% ���� && �� �н��Ⱓ 2�� �̻�
		int Q_E_Count 			= addRandomQuestionDataList(Q_E,out,_QuestionCount,_Level);// �� �н��Ⱓ 2�� �̻�
		int Q_study_count 		= addRandomQuestionDataList(Q_study,out,_QuestionCount,_Level);// 1ȸ �̻� �н�


		System.out.println("");
		System.out.println("��--------------------------------");
		System.out.println("�򰡹��� : "+Integer.toString(out.size())+"��");
		System.out.println("��ճ��̵� : "+getLevel_Avg(out));

		System.out.println("");
		
		System.out.println(Integer.toString(Q_A_Count)+"��"+"- ����� 0%");
		System.out.println(Integer.toString(Q_B_Count)+"��"+"- �ֱٿ� Ʋ�� && ����� 50% ����");
		System.out.println(Integer.toString(Q_not_study_count)+"��"+"- �ѹ��� ��Ǭ ����");
		System.out.println(Integer.toString(Q_C_Count)+"��"+"- ����� 40% ����");
		System.out.println(Integer.toString(Q_D_Count)+"��"+"- ����� 50% ���� && �� �н��Ⱓ 2�� �̻�");
		System.out.println(Integer.toString(Q_E_Count)+"��"+"- �� �н��Ⱓ 2�� �̻�");
		System.out.println(Integer.toString(Q_study_count)+"��"+"- �н��� ������ ����");

		System.out.println("");
		
		System.out.println("��5�� : "+getLevelCount(out,5)+"��	"+Integer.toString(Integer.parseInt(getLevelCount(out,5))*100/out.size())+"%");
		System.out.println("��4�� : "+getLevelCount(out,4)+"��	"+Integer.toString(Integer.parseInt(getLevelCount(out,4))*100/out.size())+"%");
		System.out.println("��3�� : "+getLevelCount(out,3)+"��	"+Integer.toString(Integer.parseInt(getLevelCount(out,3))*100/out.size())+"%");
		System.out.println("��2�� : "+getLevelCount(out,2)+"��	"+Integer.toString(Integer.parseInt(getLevelCount(out,2))*100/out.size())+"%");
		System.out.println("��1�� : "+getLevelCount(out,1)+"��	"+Integer.toString(Integer.parseInt(getLevelCount(out,1))*100/out.size())+"%");
		
		setArrangeQuestionDataList(out);
		return out;
	}
	
	
	
	public int addRandomQuestionDataList(ArrayList<QuestionInfo_AL> in, ArrayList<QuestionInfo_AL> out, int _QuestionCount, String _Level){
		 if(_Level.equals("D")){
				levelFivePercent  = 0;
				levelFourPercent  = 0;
				levelThreePercent = 0;
				levelTwoPercent   = 0;
				levelOnePercent   = 100;
		 }else if(_Level.equals("C")){
				levelFivePercent  = 0;
				levelFourPercent  = 30;
				levelThreePercent = 40;
				levelTwoPercent   = 30;
				levelOnePercent   = 0;
		 }else if(_Level.equals("B")){
				levelFivePercent  = 0;
				levelFourPercent  = 50;
				levelThreePercent = 50;
				levelTwoPercent   = 0;
				levelOnePercent   = 0;
		 }else if(_Level.equals("A")){
				levelFivePercent  = 30;
				levelFourPercent  = 40;
				levelThreePercent = 30;
				levelTwoPercent   = 0;
				levelOnePercent   = 0;
		 }else if(_Level.equals("R")){
		 }
		 
		
		int Num = 0;
		int in_Count = 0;
		Random random = new Random();
		
		while (true) {
			if (in.size() == 0 || out.size() == _QuestionCount) {
				break;
			}

			Num = random.nextInt(in.size());
			
			 if(in.get(Num).questionLevel == 1 && levelOnePercent != 0 && Integer.parseInt(getLevelCount(out, 1)) < levelOnePercent*_QuestionCount/100+1){
				 out.add(in.get(Num));
				 in_Count++;
			 }else if(in.get(Num).questionLevel == 2 && levelTwoPercent != 0 && Integer.parseInt(getLevelCount(out, 2)) < levelTwoPercent*_QuestionCount/100+1){
				 out.add(in.get(Num));
				 in_Count++;
			 }else if(in.get(Num).questionLevel == 3 && levelThreePercent != 0 && Integer.parseInt(getLevelCount(out, 3)) < levelThreePercent*_QuestionCount/100+1){
				 out.add(in.get(Num));
				 in_Count++;
			 }else if(in.get(Num).questionLevel == 4 && levelFourPercent != 0 && Integer.parseInt(getLevelCount(out, 4)) < levelFourPercent*_QuestionCount/100+1){
				 out.add(in.get(Num));
				 in_Count++;
			 }else if(in.get(Num).questionLevel == 5 && levelFivePercent != 0 && Integer.parseInt(getLevelCount(out, 5)) < levelFivePercent*_QuestionCount/100+1){
				 out.add(in.get(Num));
				 in_Count++;
			 } 
			 in.remove(Num);
		} 
		return in_Count;
	}
	
	
	/** MyNoteQuestionDataList */
	public ArrayList<QuestionInfo_AL>  getMyNoteQuestionDataList() {

		ArrayList<QuestionInfo_AL> MyNoteQuestionDataList = new ArrayList<QuestionInfo_AL>();
		for (int i = 0; i < TotalQuestionDataList.size(); i++) {
			
			if (TotalQuestionDataList.get(i).incorrectCount_Int > 0 ) { // ����Ƚ��
				MyNoteQuestionDataList.add(TotalQuestionDataList.get(i));
			}
			
		}
		setArrangeQuestionDataList(MyNoteQuestionDataList);
		
		return MyNoteQuestionDataList;
	}

	/** TotalQuestionDataList */
	public ArrayList<QuestionInfo_AL>  getTotalQuestionDataList() {
		return TotalQuestionDataList;
	}

	/** StudyQuestionDataList */
	public ArrayList<QuestionInfo_AL> getStudyQuestionDataList() {
		return StudyQuestionDataList;
	}


	/** UnitDataList */
	public ArrayList<UnitInfo_AL> getUnitDataList_UNIT_SORT() {
		return UnitDataList;
	}
	public ArrayList<UnitInfo_AL> getUnitDataList_CORRECT_SORT() {
		setArrangeUnitDataList_Correct(UnitDataList);
		return UnitDataList;
	}
	public ArrayList<UnitInfo_AL> getUnitDataList_RECENT_SORT() {
		setArrangeUnitDataList_Recent(UnitDataList);
		return UnitDataList;
	}
	
	
	public int getStudyQuestionDataList_Grading_Point() {
		return  (getCorrectCount(StudyQuestionDataList) * 100) / StudyQuestionDataList.size();
	}

	/** ListTool */
	public String getLevel_Avg(ArrayList<QuestionInfo_AL> QuestionList) {
		double sum_QLevel = 0.0;
		DecimalFormat form = new DecimalFormat("#.#");
		
		for (int i = 0; i < QuestionList.size(); i++) {
			sum_QLevel = sum_QLevel + QuestionList.get(i).questionLevel;
		}

		if (sum_QLevel > 0) {
			return form.format((double) (sum_QLevel / QuestionList.size()));
		} else {
			return "0.0";
		}
	}
	
	public String getLevelCount(ArrayList<QuestionInfo_AL> QuestionList, int level) {
		int levelCount = 0;
		for (int i = 0; i < QuestionList.size(); i++) {
			if(QuestionList.get(i).questionLevel == level ){
				levelCount++;	
			}
		}
		return Integer.toString(levelCount);
	}
	
	public String getPredictGradingPoint(ArrayList<QuestionInfo_AL> QuestionList) {
		int sum_QLevel = 0;
		double sum_QCorrectPercent = 0;

		for (int i = 0; i < QuestionList.size(); i++) {
			sum_QLevel = sum_QLevel + QuestionList.get(i).questionLevel;
			sum_QCorrectPercent = sum_QCorrectPercent + QuestionList.get(i).correctPercent_double;
		}

		if (sum_QLevel > 0) {
			return Integer.toString((int) (sum_QCorrectPercent / sum_QLevel));
		} else {
			return "0";
		}
	}

	public String getPredictCorrectCount(ArrayList<QuestionInfo_AL> QuestionList) {
		if (QuestionList.size() > 0) {

			int sum_QLevel = 0;
			double sum_QCorrectPercent = 0;

			for (int i = 0; i < QuestionList.size(); i++) {
				sum_QLevel = sum_QLevel + QuestionList.get(i).questionLevel;
				sum_QCorrectPercent = sum_QCorrectPercent + QuestionList.get(i).correctPercent_double;
			}

			return Integer.toString((int) (((sum_QCorrectPercent / sum_QLevel) * QuestionList.size()) / 100));
		} else {
			return "0";
		}
	}

	public String getGradingPoint(ArrayList<QuestionInfo_AL> QuestionList) {
		if (QuestionList.size() > 0) {
			int sum_Qpoint = 0;
			for (int i = 0; i < QuestionList.size(); i++) {
				if (QuestionList.get(i).recentStudyResultCheck.equals("O")) {
					sum_Qpoint = sum_Qpoint + 1;
				}
			}
			return Integer.toString((int) (sum_Qpoint * 100 / QuestionList.size()));
		} else {
			return "0";
		}
	}

	public int getCorrectCount(ArrayList<QuestionInfo_AL> QuestionList) {
		int sum_Qpoint = 0;

		for (int i = 0; i < QuestionList.size(); i++) {
			if (QuestionList.get(i).recentStudyResultCheck.equals("O")) {
				sum_Qpoint = sum_Qpoint + 1;
			}
		}
		return sum_Qpoint;

	}
}
