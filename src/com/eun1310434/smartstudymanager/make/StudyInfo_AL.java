package com.eun1310434.smartstudymanager.make;

public class StudyInfo_AL {
	public String 				studentName 				= ""; 	//������� �̸�
	public String 				QuestionSN 				= ""; 	//����SN
	public String 				StudyDate 				= ""; 	//�н���¥
	public String 				StudyResultCheck 		= ""; 	//�н����
	public String 				Query 					= ""; 	//�н����
	
	public StudyInfo_AL(
			String _studentName,			//�ܿ�����
			String _QuestionSN,				//�ֱ��н����
			String _StudyDate,				//�н���¥
			String _StudyResultCheck		//�н����
			){			
		this.studentName = _studentName;
		this.QuestionSN = _QuestionSN;
		this.StudyDate = _StudyDate;
		this.StudyResultCheck = _StudyResultCheck;
		this.Query = "INSERT INTO SSM_SERVER.STUDY VALUE('"+studentName+"','"+QuestionSN+"','"+Datefoamtchange(StudyDate)+"','"+StudyResultCheck+"');";
		
		//INSERT INTO SSM_SERVER.STUDY VALUE('�ڼ���','�� �ߵ����1(��)_27_199','17-8-7','O');
	}
	
	private String 						Datefoamtchange(String _date){ 
		_date = _date.replaceAll(" ", "");//��ĭ �����
		_date = _date.replace("��","-");
		_date = _date.replace("��","-");
		if(_date.contains("��")){
			_date = _date.substring(0, _date.indexOf("��"));
		}
		return _date;
	}
}
