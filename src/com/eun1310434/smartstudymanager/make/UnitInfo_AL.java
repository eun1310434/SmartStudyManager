package com.eun1310434.smartstudymanager.make;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UnitInfo_AL {
	public String 				unitSN 					= ""; 	//유형 SN

	public String 				classDate 				= ""; 	//학습날짜
	
	public String 				recentStudyResultCheck 	= ""; 	//최근학습결과
	
	public String 				correctPercent 			= ""; 	//정답률
	public double 				correctPercent_double	= -1; 	//정답률
	
	public String 				recentStudyDate 		= ""; 	//최근학습일
	public long 				recentStudyPassDate		= 0l;	//미학습기간
	
	public Color  				color 					= Color.black; 
	
	public String 				progressPercent 		= ""; 	//진도율
	public double 				progressPercent_double	= -1;	//진도율
	
	public int	 				level			 		= 0; 	//성취난이도
	
	public int 					totalStudyCount			= 0; 	//총학습횟수
	
	public String				QueryA					= "";
	public String				QueryB					= "";
	
	
	
	//최근에 틀림 	+ 자주틀리는 유형 + 학습한지 오래됨	->	핑크색
	//평소					 				->	검은색
	
	
	public UnitInfo_AL(
			String _StudentGrade,				//학생이름
			String _StudentName,				//학생이름
			String _Semester,					//학기
			String _Test,						//시험 차수
			String _classDate,					//학습날짜
			String _unitSN,						//단원정보
			String _recentStudyResultCheck,		//최근학습결과
			String _correctPercent,				//정답률
			String _recentStudyDate,			//최근학습날짜
			String _progressPercent,			//진도율
			String _level,						//획득난이도
			String _totalStudyCount				//총학습횟수
			){			
		
		
		/**학습날짜*/
		this.classDate 				= _classDate;							
		
		/**단원정보*/
		this.unitSN 				= _unitSN;
		

		/**최근학습결과*/
		this.recentStudyResultCheck = _recentStudyResultCheck;
		

		/**정답률*/
		if(_correctPercent.equals("-")){										
			this.correctPercent_double = 0.0;
			this.correctPercent 	= "0%";
		}else{
			this.correctPercent_double = Double.parseDouble(_correctPercent);
			this.correctPercent 	= _correctPercent+"%";
		}
		

		/**최근학습날짜*/
		this.recentStudyDate		= _recentStudyDate;
		this.recentStudyPassDate 	= getPastDate(classDate,recentStudyDate);
		
		
		if(recentStudyResultCheck.equals("X") && correctPercent_double <= 50.0){
			color = Color.RED;
		}
		
		
		/**진도률*/
		if(_progressPercent.equals("-")){										
			this.progressPercent_double = 0.0;
			this.progressPercent 	= "0%";
		}else{
			this.progressPercent_double = Double.parseDouble(_correctPercent);
			this.progressPercent 	= _progressPercent+"%";
		}
		

		/**총획득난이도*/
		if(_level.equals("-")){
			this.level = 0;
		}else{
			this.level = (int) Double.parseDouble(_level);
		}

		/**총학습횟수*/
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
		_date = _date.replaceAll(" ", "");//빈칸 지우기
		_date = _date.replace("년","-");
		_date = _date.replace("월","-");
		if(_date.contains("일")){
			_date = _date.substring(0, _date.indexOf("일"));
		}
		return formatter.parse(_date);
	}
}

