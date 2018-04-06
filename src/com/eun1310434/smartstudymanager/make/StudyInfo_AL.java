package com.eun1310434.smartstudymanager.make;

public class StudyInfo_AL {
	public String 				studentName 				= ""; 	//사용자의 이름
	public String 				QuestionSN 				= ""; 	//문제SN
	public String 				StudyDate 				= ""; 	//학습날짜
	public String 				StudyResultCheck 		= ""; 	//학습결과
	public String 				Query 					= ""; 	//학습결과
	
	public StudyInfo_AL(
			String _studentName,			//단원정보
			String _QuestionSN,				//최근학습결과
			String _StudyDate,				//학습날짜
			String _StudyResultCheck		//학습결과
			){			
		this.studentName = _studentName;
		this.QuestionSN = _QuestionSN;
		this.StudyDate = _StudyDate;
		this.StudyResultCheck = _StudyResultCheck;
		this.Query = "INSERT INTO SSM_SERVER.STUDY VALUE('"+studentName+"','"+QuestionSN+"','"+Datefoamtchange(StudyDate)+"','"+StudyResultCheck+"');";
		
		//INSERT INTO SSM_SERVER.STUDY VALUE('박성준','쎈 중등수학1(상)_27_199','17-8-7','O');
	}
	
	private String 						Datefoamtchange(String _date){ 
		_date = _date.replaceAll(" ", "");//빈칸 지우기
		_date = _date.replace("년","-");
		_date = _date.replace("월","-");
		if(_date.contains("일")){
			_date = _date.substring(0, _date.indexOf("일"));
		}
		return _date;
	}
}
