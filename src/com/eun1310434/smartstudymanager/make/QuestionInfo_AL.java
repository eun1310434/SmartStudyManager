package com.eun1310434.smartstudymanager.make;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eun1310434.smartstudymanager.tool.ColorList;

public class QuestionInfo_AL {
	public String 				questionSN 				= ""; 	//문제 SN
	
	public String 				classDate 				= ""; 	//학습날짜
	
	public String 				question_path 			= ""; 	//문제경로
	public String 				solution_path 			= ""; 	//답지경로
	public String 				workbook_name 			= ""; 	//교재이름
	public String 				questionPage	 		= ""; 	//문제페이지
	public String 				questionNum	 			= ""; 	//문제번호
	public int 	  				questionLevel	 		= -1; 	//문제난이도
	public String 				questionUnit 			= ""; 	//문제단원
	public String 				questionType 			= ""; 	//문제구분 (서술형, 주관식, 객관식)
	public String 				recentStudyDate 		= ""; 	//최근학습일
	public long 				recentStudyPassDate		= 0l;	//미학습기간
	
	public String 				recentStudyResultCheck 	= ""; 	//최근학습결과
	public Color  				recentStudyResultColor 	= null; //최근학습결과색
	public String 				correctPercent 			= ""; 	//정답률
	public double 				correctPercent_double	= -1; 	//정답률
	public String 				totalCount 				= ""; 	//학습횟수
	public int    				totalCount_Int			= -1; 	//학습횟수
	public String 				correctCount 			= ""; 	//정답횟수
	public int    				correctCount_Int		= -1; 	//정답횟수
	public String 				incorrectCount 			= ""; 	//오답횟수
	public int    				incorrectCount_Int 		= -1; 	//오답횟수
	public String				Query					= "";
	
	
	
	public QuestionInfo_AL(
			String _classDate,				//학습날짜
			String _questionPath, 			//문제경로
			String _solutionPath, 			//답지경로
			String _workBook_name,			//교재이름
			String _questionPage,			//문제페이지
			String _questionNum,			//문제번호
			String _questionLevel,			//문제난이도
			String _questionUnit,			//문제단원
			String _questionType,			//문제구분 (서술형, 주관식, 객관식)
			String _recentStudyDate,		//최근학습일
			String _recentStudyResultCheck,	//최근학습결과(O, X, ?)
			String _correctPercent,			//정답률
			String _correctCount,			//정답횟수
			String _incorrectCount){		//오답횟수

		this.classDate 					= _classDate;							//학습날짜
		this.question_path 				= _questionPath;						//문제경로
		this.solution_path 				= _solutionPath;						//답지경로
		this.workbook_name 				= _workBook_name;						//교재이름
		this.questionPage				= _questionPage;						//문제페이지
		this.questionNum				= _questionNum;							//문제번호
		this.questionLevel				= Integer.parseInt(_questionLevel);		//문제난이도
		this.questionUnit 				= _questionUnit;						//문제단원
		this.questionType				= _questionType;						//문제구분 (서술형, 주관식, 객관식)
		
		
		this.recentStudyDate			= _recentStudyDate;						//최근학습일
		this.recentStudyResultCheck		= _recentStudyResultCheck;				//최근학습결과(O, X, ?, -)
		if(_recentStudyResultCheck.equals("O")){								//최근학습결과색
			this.recentStudyResultColor	= ColorList.BLUE;
		}else if(_recentStudyResultCheck.equals("X")){	
			this.recentStudyResultColor	= ColorList.RED;
		}else if(_recentStudyResultCheck.equals("?")){	
			this.recentStudyResultColor	= ColorList.PURPLE;
		}else if(_recentStudyResultCheck.equals("-")){	
			this.recentStudyResultColor	= ColorList.GREEN;
		}
		
		
		if(_correctPercent.equals("-")){										//정답률
			this.correctPercent_double = 0.0;
			this.correctPercent 	= "0 %";
		}else{
			this.correctPercent_double = Double.parseDouble(_correctPercent);
			this.correctPercent 	= _correctPercent+" %";
		}
		
		
		
		if(_correctCount.equals("-") && _incorrectCount.equals("-")){
			this.totalCount			= "- 회";
			this.totalCount_Int		= 0;
		}else if(_correctCount.equals("-")){
			this.totalCount			= _incorrectCount+" 회";
			this.totalCount_Int		= Integer.parseInt(_incorrectCount);
		}else if(_incorrectCount.equals("-")){
			this.totalCount			= _correctCount+" 회";
			this.totalCount_Int		= Integer.parseInt(_correctCount);
		}else{
			this.totalCount			= Integer.toString(Integer.parseInt(_correctCount) + Integer.parseInt(_incorrectCount))+" 회";
			this.totalCount_Int		= Integer.parseInt(_correctCount) + Integer.parseInt(_incorrectCount);
		}
		
		
		this.correctCount 			= _correctCount+" 회";
		if(_correctCount.equals("-")){
			this.correctCount_Int	= 0;
		}else{
			this.correctCount_Int	= Integer.parseInt(_correctCount);
		}
		
		this.incorrectCount 		= _incorrectCount+" 회";
		this.incorrectCount_Int		= this.totalCount_Int - this.correctCount_Int;
		
		this.questionSN 			= this.workbook_name+"_"+this.questionPage+"_"+this.questionNum;
		
		
		this.recentStudyPassDate = getPastDate(classDate,recentStudyDate);
		
		
		
		this.Query = 
				"INSERT INTO SSM_SERVER.QUESTION VALUE('"
						+questionSN+"','"
						+workbook_name+"','"
						+questionPage+"','"
						+questionNum+"','"
						+questionLevel+"','"
						+questionUnit+"','"
						+questionType+"');";

		//INSERT INTO SSM_SERVER.QUESTION VALUE('쎈 중등수학2(하)_147_831','C:\\Users\\ddil\\Documents\\MYS\\BSN\\[BSN] 스마트 스터디 매니저 교육원\\Application\\PC\\MakeWorkBook\\Resource\\Question\\쎈 중등수학2(하)\\쎈 중등수학2(하)_0147_0831.jpg','C:\\Users\\ddil\\Documents\\MYS\\BSN\\[BSN] 스마트 스터디 매니저 교육원\\Application\\PC\\MakeWorkBook\\Resource\\Solution\\쎈 중등수학2(하) 정답\\쎈 중등수학2(하) 정답_0079_0831.jpg','쎈 중등수학2(하)',147,831,1,'수학-중2-21-선행1','주관식');
		
	}
	
	private long						getPastDate(String _classDate, String _pastDate){
		long out = -1l;
		if(_pastDate.equals("-")){
			out = -1l;
		}else{
			try {
				Date pastDate = Datefoamtchange(_pastDate);
				Date classDate = Datefoamtchange(_classDate);
				out = (classDate.getTime() - pastDate.getTime()) / (24 * 60 * 60 * 1000);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out = 0l;
			}
		}
		return out;
	}

	

	private SimpleDateFormat 			formatter 				= new SimpleDateFormat("yy-MM-dd");
	private Date 						Datefoamtchange(String _date) throws ParseException{ 
		_date = _date.replaceAll(" ", "");//빈칸 지우기
		_date = _date.replace("년","-");
		_date = _date.replace("월","-");
		if(_date.contains("일")){
			_date = _date.substring(0, _date.indexOf("일"));
		}
		return formatter.parse(_date);
	}
	
}

