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

	public String  StudentName 		=	"";	//이름
	public String  StudentSubject 	=	"";	//과목	ex) 수학,토익(문법)
	public String  StudentGrade 	=	"";	//학년	ex) 중1,중2,중3,고1,고2,고3
	public String  StudentSemester 	=	"";	//학기	ex) 1학기, 2학기
	public String  StudentTest 		=	"";	//평가	ex) 1차, 2차
	public String  TestDate 		=	"";	//날짜	ex) 17년 8월 21일 월요일 , 17-08-21, 17-8-21
	
	public String  GradeSemester	=	"";
	public String  ClassName		=	"";
	public String  MyNoteName		=	"";
	

	public String  preditPoint		=	"";
	public String  correctPercent	=	"";	//정답률
	public String  progressPercent	=	"";	//진도율
	public String  studyLevel		=	""; //난이도
	public String  studyCount		=	""; //학습횟수
	private ArrayList<QuestionInfo_AL> 	TotalQuestionDataList 	= null; // 전체문제
	private ArrayList<QuestionInfo_AL> 	StudyQuestionDataList 	= null; // 학습문제
	private ArrayList<UnitInfo_AL> 		UnitDataList 			= null; // 유형
	public ArrayList<StudyInfo_AL> 	StudyDataList 			= null; // 유형
	
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

		StudentName 		=	_StudentName;		//이름
		StudentSubject 		=	_StudentSubject;	//과목	ex) 수학,토익(문법)
		StudentGrade 		=	_StudentGrade;		//학년	ex) 중1,중2,중3,고1,고2,고3
		StudentSemester 	=	_StudentSemester;	//학기	ex) 1학기, 2학기
		StudentTest 		=	_StudentTest;		//평가	ex) 1차, 2차
		TestDate 			=	_TestDate;			//날짜	ex) 17년 8월 21일 월요일 , 17-08-21, 17-8-21
		GradeSemester		=	StudentGrade + " - " + StudentSemester + " - " + StudentTest;
		ClassName			=	"[SSM] " + StudentName +"_"+StudentSubject+"_"+StudentGrade+"_"+StudentSemester+"_"+StudentTest+ "(" + TestDate+")" ;
		MyNoteName			=	StudentGrade+"_"+StudentSemester+"_"+StudentTest;
		

		if (wbGet != null) {
			setInfoData();
			//setQuestionDataList_mysql();
			setStudyQuestionDataList_excel();	// 학습문제 셋팅
			setTotalQuestionDataList_excel(); 	// 전체문제 셋팅
			setUnitDataList_excel();			// 단원별 셋팅
			setStudyDataList_excel();			// 단원별 셋팅
		}
	}

	private void setInfoData() {
		Sheet sheet = wbGet.getSheet("printInfo");// printDB이라는 시트를 불러옴

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.
			
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

	private void setQuestionDataList_mysql() { // 진도문제 셋팅
		SSM_SERVER_DB d = new SSM_SERVER_DB();
		StudyQuestionDataList = d.Question_DATA_SET(StudentName,StudentSubject, StudentGrade, StudentSemester, StudentTest);
		d.quit();
	}
	
	private void setTotalQuestionDataList_excel() { // 진도문제 셋팅
		String questionPath = "";
		TotalQuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printTotalQuestionDataList");// printTotalQuestionDataList이라는
																	// 시트를 불러옴,
																	// 명칭철저!

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell의 데이터들을
																// 들고옵니다.
				questionPath = cells.next().getStringCellValue();

				if (questionPath.equals("-")) {
					break;
				}

				TotalQuestionDataList.add(new QuestionInfo_AL(
						TestDate, 							// 평가날짜
															// _classDate
						questionPath, 						// 문제파일 경로
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
						cells.next().getStringCellValue(), 	// 최근학습일 -
															// _recentStudyDate
						cells.next().getStringCellValue(), 	// 최근학습결과 -
															// _recentStudyResultCheck
						cells.next().getStringCellValue(), 	// 정답률 -
															// _correctPercent
						cells.next().getStringCellValue(), 	// 정답횟수 -
															// _correctCount
						cells.next().getStringCellValue()));// 오답횟수 -
															// _incorrectCount
			}
		}
		setArrangeQuestionDataList(TotalQuestionDataList);
	}

	private void setStudyQuestionDataList_excel() { // 학습문제 셋팅
		String questionPath = "";
		StudyQuestionDataList = new ArrayList<QuestionInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudyQuestionDataList");// printStudyQuestionDataList이라는
																	// 시트를 불러옴,
																	// 명칭철저!

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell의 데이터들을
																// 들고옵니다.
				questionPath = cells.next().getStringCellValue();

				if (questionPath.equals("-")) {
					break;
				}

				StudyQuestionDataList.add(new QuestionInfo_AL(
						TestDate, 							// 평가날짜
					 										// _classDate
						questionPath, 						// 문제파일 경로
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
						cells.next().getStringCellValue(), 	// 최근학습일 -
															// _recentStudyDate
						cells.next().getStringCellValue(), 	// 최근학습결과 -
															// _recentStudyResultCheck
						cells.next().getStringCellValue(), 	// 정답률 -
															// _correctPercent
						cells.next().getStringCellValue(), 	// 정답횟수 -
															// _correctCount
						cells.next().getStringCellValue()));// 오답횟수 -
															// _incorrectCount
			}
		}
		setArrangeQuestionDataList(StudyQuestionDataList);
	}

	/** setArrangeQuestionDataList */
	public void  setArrangeQuestionDataList(ArrayList<QuestionInfo_AL> ArrangeQuestionDataList) {
        // 단원별 정렬
		QuestionInfo_UNIT_SORT level_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, level_sort);
        
		QuestionInfo_UNIT_SORT unit_sort = new QuestionInfo_UNIT_SORT();
        Collections.sort(ArrangeQuestionDataList, unit_sort);
	}
	

	
	private void setUnitDataList_excel()  {  // 학습유형 셋팅
		String unitSN = "";
		UnitDataList = new ArrayList<UnitInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudyUnitDataList");// printStudyUnitDataList이라는
																// 시트를 불러옴,
																// 명칭철저!

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell의 데이터들을
																// 들고옵니다.
				unitSN = cells.next().getStringCellValue();

				if (unitSN.equals("-")) {
					break;
				}

				UnitDataList.add(new UnitInfo_AL(
						StudentGrade,
						StudentName,
						StudentSemester,
						StudentTest,
						TestDate, 								// 평가날짜
					 											// _classDate
						unitSN, 								// 단원정보
														 		// _unitSN
						cells.next().getStringCellValue(), 		// 최근학습결과
																// _recentStudyResultCheck
						cells.next().getStringCellValue(), 		// 정답률
																// _correctPercent
						cells.next().getStringCellValue(), 		// 최근학습날짜
																// _recentStudyDate
						cells.next().getStringCellValue(), 		// 진도율
																// _progressPercent
						cells.next().getStringCellValue(), 		// 획득난이도
																// _level
						cells.next().getStringCellValue())); 	// 총학습횟수
																// _totalStudyCount
			}
		}
	}

	/** setArrangeUnitDataList_Unit*/
	public void  setArrangeUnitDataList_Unit(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // 최근학습 정렬
		UnitInfo_UNIT_SORT unit_unit_sort = new UnitInfo_UNIT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_unit_sort);
	}

	/** setArrangeUnitDataList_Correct */
	public void  setArrangeUnitDataList_Correct(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // 정답률 정렬
		UnitInfo_CORRECT_SORT unit_correct_sort = new UnitInfo_CORRECT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_correct_sort);
	}

	/** setArrangeUnitDataList_Recent */
	public void  setArrangeUnitDataList_Recent(ArrayList<UnitInfo_AL> _ArrangeUnitDataList) {
        // 최근학습 정렬
		UnitInfo_RECENT_SORT unit_recent_sort = new UnitInfo_RECENT_SORT();
        Collections.sort(_ArrangeUnitDataList, unit_recent_sort);
	}
	
	

	/** 학습기록 데이터 추출 */
	private void setStudyDataList_excel()  {  // 학습유형 셋팅
		String questionSN = "";
		StudyDataList = new ArrayList<StudyInfo_AL>();

		Sheet sheet = wbGet.getSheet("printStudy");	// printStudy이라는
													// 시트를 불러옴,명칭철저!

		if (sheet != null) {// 해당 시트가 있는지 확인
			Iterator<Row> rows = sheet.rowIterator(); // row의 데이터들을 들고옵니다.

			while (rows.hasNext()) {
				Iterator<Cell> cells = rows.next().iterator(); // Cell의 데이터들을  들고옵니다.
				questionSN = cells.next().getStringCellValue();

				if (questionSN.equals("-")) {
					break;
				}

				StudyDataList.add(new StudyInfo_AL(
						StudentName, 							// 학생이름
					 											// _classDate
						questionSN, 							// 문제정보
														 		// _questionSN
						cells.next().getStringCellValue(), 		// 학습날짜
																// _StudyDate
						cells.next().getStringCellValue())); 	// 학습결과
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

		System.out.println("학습--------------------------------");
		System.out.println("전체개수    : "+Integer.toString(_QuestionDataList.size()));
		System.out.println("평균난이도 : "+getLevel_Avg(_QuestionDataList));
		
		levelFiveCount = Integer.parseInt(getLevelCount(_QuestionDataList,5));
		levelFivePercent = levelFiveCount*100/_QuestionDataList.size();
		System.out.println("별5개       : "+Integer.toString(levelFiveCount)+"개	"+Integer.toString(levelFivePercent)+"%");
		
		levelFourCount = Integer.parseInt(getLevelCount(_QuestionDataList,4));
		levelFourPercent = levelFourCount*100/_QuestionDataList.size();
		System.out.println("별4개       : "+Integer.toString(levelFourCount)+"개	"+Integer.toString(levelFourPercent)+"%");

		levelThreeCount = Integer.parseInt(getLevelCount(_QuestionDataList,3));
		levelThreePercent = levelThreeCount*100/_QuestionDataList.size();
		System.out.println("별3개       : "+Integer.toString(levelThreeCount)+"개	"+Integer.toString(levelThreePercent)+"%");

		levelTwoCount = Integer.parseInt(getLevelCount(_QuestionDataList,2));
		levelTwoPercent = levelTwoCount*100/_QuestionDataList.size();
		System.out.println("별2개       : "+Integer.toString(levelTwoCount)+"개	"+Integer.toString(levelTwoPercent)+"%");
		
		levelOneCount = Integer.parseInt(getLevelCount(_QuestionDataList,1));
		levelOnePercent = levelOneCount*100/_QuestionDataList.size();
		System.out.println("별1개       : "+Integer.toString(levelOneCount)+"개	"+Integer.toString(levelOnePercent)+"%");
		
		
		 ArrayList<QuestionInfo_AL> out = new ArrayList<QuestionInfo_AL>();
		 
		 /**문제 추출*/
		 //최근틀린문제, 정답률 낮은 문제 , 한번도 안푼문제, 학습한지 오래된문제
		 ArrayList<QuestionInfo_AL> Q_not_study = new ArrayList<QuestionInfo_AL>(); // 한번도 안푼 문제
		 
		 ArrayList<QuestionInfo_AL> Q_A = new ArrayList<QuestionInfo_AL>(); // 정답률 0%
		 
		 ArrayList<QuestionInfo_AL> Q_B = new ArrayList<QuestionInfo_AL>(); // 최근에 틀림 && 정답률 50% 이하
		 
		 ArrayList<QuestionInfo_AL> Q_C = new ArrayList<QuestionInfo_AL>(); // 정답률 40% 이하
		 
		 ArrayList<QuestionInfo_AL> Q_D = new ArrayList<QuestionInfo_AL>(); // 정답률 50% 이하 && 미 학습기간 2주 이상
		 
		 ArrayList<QuestionInfo_AL> Q_E = new ArrayList<QuestionInfo_AL>(); // 미 학습기간 2주 이상

		 ArrayList<QuestionInfo_AL> Q_study = new ArrayList<QuestionInfo_AL>(); // 1회 이상 학습
		 
		 
		 
		 for(int i = 0 ; i < _QuestionDataList.size(); i++){
			 
			 if(_QuestionDataList.get(i).correctPercent_double == 0.0 && _QuestionDataList.get(i).totalCount_Int > 0){// 정답률 0%
				 Q_A.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).recentStudyResultCheck.equals("X") && _QuestionDataList.get(i).correctPercent_double <= 50.0){// 최근에 틀림 && 정답률 50% 이하
				 Q_B.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).correctPercent_double <= 40.0 && _QuestionDataList.get(i).totalCount_Int > 0){ // 정답률 40% 이하
				 Q_C.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).correctPercent_double <= 50.0 && _QuestionDataList.get(i).recentStudyPassDate >= 14){ // 정답률 50% 이하 && 미 학습기간 2주 이상
				 Q_D.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).recentStudyPassDate >= 14 ){ //  미 학습기간 2주 이상
				 Q_E.add(_QuestionDataList.get(i));
				 
			 }else if(_QuestionDataList.get(i).totalCount_Int >= 1){// 1회 이상 학습
				 Q_study.add(_QuestionDataList.get(i));
				 
			 }else{// 한번도 안푼 문제
				 Q_not_study.add(_QuestionDataList.get(i));
			 }
		 }

		System.out.println(Integer.toString(Q_A.size())+"개"+"- 정답률 0%");
		System.out.println(Integer.toString(Q_B.size())+"개"+"- 최근에 틀림 && 정답률 50% 이하");
		System.out.println(Integer.toString(Q_not_study.size())+"개"+"- 학습 안푼 문제");
		System.out.println(Integer.toString(Q_C.size())+"개"+"- 정답률 40% 이하");
		System.out.println(Integer.toString(Q_D.size())+"개"+"- 정답률 50% 이하 && 미 학습기간 2주 이상");
		System.out.println(Integer.toString(Q_E.size())+"개"+"- 미 학습기간 2주 이상 ");
		System.out.println(Integer.toString(Q_study.size())+"개"+"- 학습한 나머지 문제");

		/**문제입력*/
		int Q_A_Count 			= addRandomQuestionDataList(Q_A,out,_QuestionCount,_Level);// 정답률 0%
		int Q_B_Count 			= addRandomQuestionDataList(Q_B,out,_QuestionCount,_Level);// 최근에 틀림
		int Q_not_study_count 	= addRandomQuestionDataList(Q_not_study,out,_QuestionCount,_Level);// 한번도 안푼 문제
		int Q_C_Count 			= addRandomQuestionDataList(Q_C,out,_QuestionCount,_Level);// 정답률 40% 이하
		int Q_D_Count 			= addRandomQuestionDataList(Q_D,out,_QuestionCount,_Level);// 정답률 50% 이하 && 미 학습기간 2주 이상
		int Q_E_Count 			= addRandomQuestionDataList(Q_E,out,_QuestionCount,_Level);// 미 학습기간 2주 이상
		int Q_study_count 		= addRandomQuestionDataList(Q_study,out,_QuestionCount,_Level);// 1회 이상 학습


		System.out.println("");
		System.out.println("평가--------------------------------");
		System.out.println("평가문제 : "+Integer.toString(out.size())+"개");
		System.out.println("평균난이도 : "+getLevel_Avg(out));

		System.out.println("");
		
		System.out.println(Integer.toString(Q_A_Count)+"개"+"- 정답률 0%");
		System.out.println(Integer.toString(Q_B_Count)+"개"+"- 최근에 틀림 && 정답률 50% 이하");
		System.out.println(Integer.toString(Q_not_study_count)+"개"+"- 한번도 안푼 문제");
		System.out.println(Integer.toString(Q_C_Count)+"개"+"- 정답률 40% 이하");
		System.out.println(Integer.toString(Q_D_Count)+"개"+"- 정답률 50% 이하 && 미 학습기간 2주 이상");
		System.out.println(Integer.toString(Q_E_Count)+"개"+"- 미 학습기간 2주 이상");
		System.out.println(Integer.toString(Q_study_count)+"개"+"- 학습한 나머지 문제");

		System.out.println("");
		
		System.out.println("별5개 : "+getLevelCount(out,5)+"개	"+Integer.toString(Integer.parseInt(getLevelCount(out,5))*100/out.size())+"%");
		System.out.println("별4개 : "+getLevelCount(out,4)+"개	"+Integer.toString(Integer.parseInt(getLevelCount(out,4))*100/out.size())+"%");
		System.out.println("별3개 : "+getLevelCount(out,3)+"개	"+Integer.toString(Integer.parseInt(getLevelCount(out,3))*100/out.size())+"%");
		System.out.println("별2개 : "+getLevelCount(out,2)+"개	"+Integer.toString(Integer.parseInt(getLevelCount(out,2))*100/out.size())+"%");
		System.out.println("별1개 : "+getLevelCount(out,1)+"개	"+Integer.toString(Integer.parseInt(getLevelCount(out,1))*100/out.size())+"%");
		
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
			
			if (TotalQuestionDataList.get(i).incorrectCount_Int > 0 ) { // 오답횟수
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
