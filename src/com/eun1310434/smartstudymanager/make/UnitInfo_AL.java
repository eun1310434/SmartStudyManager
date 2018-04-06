package com.eun1310434.smartstudymanager.make;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnitInfo_AL {
	public String 				unitSN 					= ""; 	//���� SN

	public String 				classDate 				= ""; 	//�н���¥
	
	public String 				recentStudyResultCheck 	= ""; 	//�ֱ��н����
	
	public String 				correctPercent 			= ""; 	//�����
	public double 				correctPercent_double	= -1; 	//�����
	
	public String 				recentStudyDate 		= ""; 	//�ֱ��н���
	public long 				recentStudyPassDate		= 0l;	//���н��Ⱓ
	
	public Color  				color 					= Color.black; 
	
	public String 				progressPercent 		= ""; 	//������
	public double 				progressPercent_double	= -1;	//������
	
	public int	 				level			 		= 0; 	//���볭�̵�
	
	public int 					totalStudyCount			= 0; 	//���н�Ƚ��
	
	public String				QueryA					= "";
	public String				QueryB					= "";
	
	
	
	//�ֱٿ� Ʋ�� 	+ ����Ʋ���� ���� + �н����� ������	->	��ũ��
	//���					 				->	������
	
	
	public UnitInfo_AL(
			String _StudentGrade,				//�л��̸�
			String _StudentName,				//�л��̸�
			String _Semester,					//�б�
			String _Test,						//���� ����
			String _classDate,					//�н���¥
			String _unitSN,						//�ܿ�����
			String _recentStudyResultCheck,		//�ֱ��н����
			String _correctPercent,				//�����
			String _recentStudyDate,			//�ֱ��н���¥
			String _progressPercent,			//������
			String _level,						//ȹ�泭�̵�
			String _totalStudyCount				//���н�Ƚ��
			){			
		
		
		/**�н���¥*/
		this.classDate 				= _classDate;							
		
		/**�ܿ�����*/
		this.unitSN 				= _unitSN;
		

		/**�ֱ��н����*/
		this.recentStudyResultCheck = _recentStudyResultCheck;
		

		/**�����*/
		if(_correctPercent.equals("-")){										
			this.correctPercent_double = 0.0;
			this.correctPercent 	= "0%";
		}else{
			this.correctPercent_double = Double.parseDouble(_correctPercent);
			this.correctPercent 	= _correctPercent+"%";
		}
		

		/**�ֱ��н���¥*/
		this.recentStudyDate		= _recentStudyDate;
		this.recentStudyPassDate 	= getPastDate(classDate,recentStudyDate);
		
		
		if(recentStudyResultCheck.equals("X") && correctPercent_double <= 50.0){
			color = Color.RED;
		}
		
		
		/**������*/
		if(_progressPercent.equals("-")){										
			this.progressPercent_double = 0.0;
			this.progressPercent 	= "0%";
		}else{
			this.progressPercent_double = Double.parseDouble(_correctPercent);
			this.progressPercent 	= _progressPercent+"%";
		}
		

		/**��ȹ�泭�̵�*/
		if(_level.equals("-")){
			this.level = 0;
		}else{
			this.level = (int) Double.parseDouble(_level);
		}

		/**���н�Ƚ��*/
		if(_totalStudyCount.equals("-")){
			this.totalStudyCount = 0;
		}else{
			this.totalStudyCount = Integer.parseInt(_totalStudyCount);
		}
	
		
		
		String tmp 		=	unitSN;
		String subject 	=	tmp.substring(0, tmp.indexOf("-"));
		tmp				=	tmp.substring(tmp.indexOf("-")+1, tmp.length());
		String grade	=	tmp.substring(0, tmp.indexOf("-"));
		tmp				=	tmp.substring(tmp.indexOf("-")+1, tmp.length());
		String unit_num	=	tmp.substring(0, tmp.indexOf("-"));
		tmp				=	tmp.substring(tmp.indexOf("-")+1, tmp.length());
		String step		=	tmp;
		
		
		this.QueryA = "INSERT INTO SSM_SERVER.UNIT VALUE('"+unitSN+"','"+subject+"','"+grade+"','"+unit_num+"','"+step+"','"+unitSN+"');";
		this.QueryB = "INSERT INTO SSM_SERVER.TEST VALUE('"+_StudentName+"','"+unitSN+"','"+_StudentGrade+"','"+_Semester+"','"+_Test+"');";
		
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

