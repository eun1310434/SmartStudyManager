package com.eun1310434.smartstudymanager.make;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.eun1310434.smartstudymanager.tool.ColorList;

public class QuestionInfo_AL {
	public String 				questionSN 				= ""; 	//���� SN
	
	public String 				classDate 				= ""; 	//�н���¥
	
	public String 				question_path 			= ""; 	//�������
	public String 				solution_path 			= ""; 	//�������
	public String 				workbook_name 			= ""; 	//�����̸�
	public String 				questionPage	 		= ""; 	//����������
	public String 				questionNum	 			= ""; 	//������ȣ
	public int 	  				questionLevel	 		= -1; 	//�������̵�
	public String 				questionUnit 			= ""; 	//�����ܿ�
	public String 				questionType 			= ""; 	//�������� (������, �ְ���, ������)
	public String 				recentStudyDate 		= ""; 	//�ֱ��н���
	public long 				recentStudyPassDate		= 0l;	//���н��Ⱓ
	
	public String 				recentStudyResultCheck 	= ""; 	//�ֱ��н����
	public Color  				recentStudyResultColor 	= null; //�ֱ��н������
	public String 				correctPercent 			= ""; 	//�����
	public double 				correctPercent_double	= -1; 	//�����
	public String 				totalCount 				= ""; 	//�н�Ƚ��
	public int    				totalCount_Int			= -1; 	//�н�Ƚ��
	public String 				correctCount 			= ""; 	//����Ƚ��
	public int    				correctCount_Int		= -1; 	//����Ƚ��
	public String 				incorrectCount 			= ""; 	//����Ƚ��
	public int    				incorrectCount_Int 		= -1; 	//����Ƚ��
	public String				Query					= "";
	
	
	
	public QuestionInfo_AL(
			String _classDate,				//�н���¥
			String _questionPath, 			//�������
			String _solutionPath, 			//�������
			String _workBook_name,			//�����̸�
			String _questionPage,			//����������
			String _questionNum,			//������ȣ
			String _questionLevel,			//�������̵�
			String _questionUnit,			//�����ܿ�
			String _questionType,			//�������� (������, �ְ���, ������)
			String _recentStudyDate,		//�ֱ��н���
			String _recentStudyResultCheck,	//�ֱ��н����(O, X, ?)
			String _correctPercent,			//�����
			String _correctCount,			//����Ƚ��
			String _incorrectCount){		//����Ƚ��

		this.classDate 					= _classDate;							//�н���¥
		this.question_path 				= _questionPath;						//�������
		this.solution_path 				= _solutionPath;						//�������
		this.workbook_name 				= _workBook_name;						//�����̸�
		this.questionPage				= _questionPage;						//����������
		this.questionNum				= _questionNum;							//������ȣ
		this.questionLevel				= Integer.parseInt(_questionLevel);		//�������̵�
		this.questionUnit 				= _questionUnit;						//�����ܿ�
		this.questionType				= _questionType;						//�������� (������, �ְ���, ������)
		
		
		this.recentStudyDate			= _recentStudyDate;						//�ֱ��н���
		this.recentStudyResultCheck		= _recentStudyResultCheck;				//�ֱ��н����(O, X, ?, -)
		if(_recentStudyResultCheck.equals("O")){								//�ֱ��н������
			this.recentStudyResultColor	= ColorList.BLUE;
		}else if(_recentStudyResultCheck.equals("X")){	
			this.recentStudyResultColor	= ColorList.RED;
		}else if(_recentStudyResultCheck.equals("?")){	
			this.recentStudyResultColor	= ColorList.PURPLE;
		}else if(_recentStudyResultCheck.equals("-")){	
			this.recentStudyResultColor	= ColorList.GREEN;
		}
		
		
		if(_correctPercent.equals("-")){										//�����
			this.correctPercent_double = 0.0;
			this.correctPercent 	= "0 %";
		}else{
			this.correctPercent_double = Double.parseDouble(_correctPercent);
			this.correctPercent 	= _correctPercent+" %";
		}
		
		
		
		if(_correctCount.equals("-") && _incorrectCount.equals("-")){
			this.totalCount			= "- ȸ";
			this.totalCount_Int		= 0;
		}else if(_correctCount.equals("-")){
			this.totalCount			= _incorrectCount+" ȸ";
			this.totalCount_Int		= Integer.parseInt(_incorrectCount);
		}else if(_incorrectCount.equals("-")){
			this.totalCount			= _correctCount+" ȸ";
			this.totalCount_Int		= Integer.parseInt(_correctCount);
		}else{
			this.totalCount			= Integer.toString(Integer.parseInt(_correctCount) + Integer.parseInt(_incorrectCount))+" ȸ";
			this.totalCount_Int		= Integer.parseInt(_correctCount) + Integer.parseInt(_incorrectCount);
		}
		
		
		this.correctCount 			= _correctCount+" ȸ";
		if(_correctCount.equals("-")){
			this.correctCount_Int	= 0;
		}else{
			this.correctCount_Int	= Integer.parseInt(_correctCount);
		}
		
		this.incorrectCount 		= _incorrectCount+" ȸ";
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

		//INSERT INTO SSM_SERVER.QUESTION VALUE('�� �ߵ����2(��)_147_831','C:\\Users\\ddil\\Documents\\MYS\\BSN\\[BSN] ����Ʈ ���͵� �Ŵ��� ������\\Application\\PC\\MakeWorkBook\\Resource\\Question\\�� �ߵ����2(��)\\�� �ߵ����2(��)_0147_0831.jpg','C:\\Users\\ddil\\Documents\\MYS\\BSN\\[BSN] ����Ʈ ���͵� �Ŵ��� ������\\Application\\PC\\MakeWorkBook\\Resource\\Solution\\�� �ߵ����2(��) ����\\�� �ߵ����2(��) ����_0079_0831.jpg','�� �ߵ����2(��)',147,831,1,'����-��2-21-����1','�ְ���');
		
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
		_date = _date.replaceAll(" ", "");//��ĭ �����
		_date = _date.replace("��","-");
		_date = _date.replace("��","-");
		if(_date.contains("��")){
			_date = _date.substring(0, _date.indexOf("��"));
		}
		return formatter.parse(_date);
	}
	
}

