package com.eun1310434.smartstudymanager.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;

import com.eun1310434.smartstudymanager.make.GetStudentData;
import com.eun1310434.smartstudymanager.make.UnitInfo_AL;

public class DrawString {
	private DrawImage 			DI 					= new DrawImage();

	public void							drawStringFormulaInfo(
			Graphics _g, 
			String _UnitName ) throws IOException{
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.BOLD, 
				Size.TXT_B_S, 
				ColorList.WHITE, 
				_UnitName, 
				Size.FORMULA_UNIT_NAME_WIDTH, Size.FORMULA_UNIT_NAME_HEIGHT);
	}

	public void							drawStringTestCoverInfo(
			Graphics _g, 
			String _level,
			GetStudentData 	_gsdb ) throws IOException{
		
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_B_B,
				ColorList.GREEN,
				_level,			
				830,	1416);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.TestDate,			
				940,	2418);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentSubject,			
				940,	2518);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.GradeSemester,			
				940,	2616);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentName,			
				940,	2719);
	}


	public void							drawStringStudyResultInfo(
			Graphics _g, 
			GetStudentData 	_gsdb ) throws IOException{
	

		//ÇÐ»ýÁ¤º¸
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentName,			
				1201,	540);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentSubject,			
				1201,	610);
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.GradeSemester,			
				1201,	680);

		
		
		
		//ÇÐ½ÀÁ¤º¸
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_B_M,
				ColorList.GRAY_DARK,
				_gsdb.preditPoint,			
				400,	1473);//¿¹»óÁ¡¼ö
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_M,
				ColorList.GRAY_DARK,
				_gsdb.correctPercent+"%",			
				1145,	1236);//Á¤´ä·ü

		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_M,
				ColorList.GRAY_DARK,
				_gsdb.progressPercent+"%",			
				1605,	1236);//ÁøÇà·ü
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_M,
				ColorList.GRAY_DARK,
				"LV."+_gsdb.studyLevel,			
				1145,	1600);//³­ÀÌµµ

		drawString(
				_g,
				"¸¼Àº °íµñ",
				 Font.PLAIN,
				 Size.TXT_B_M,
				ColorList.GRAY_DARK,
				_gsdb.studyCount,			
				1625,	1600);//ÇÐ½ÀÈ½¼ö
		
		
		//À¯ÇüÁ¤º¸
		//ÃÖ±Ù¿¡ Æ²¸° À¯Çü
		ArrayList<UnitInfo_AL> 		UnitDataList_CORRECT_SORT 			= _gsdb.getUnitDataList_CORRECT_SORT(); // À¯Çü
		int width = 200;
		int height = 0;
		int rowCount = 0;
		int i = 0;
		while(true){
			if(rowCount == 10){
				width = 200 + 450;
				height = 0;
			}
			
			if(rowCount > 19 || i == UnitDataList_CORRECT_SORT.size()){
				break;
			}
			
			if(UnitDataList_CORRECT_SORT.get(i).recentStudyResultCheck.equals("X")){
				drawString(
						_g,
						"¸¼Àº °íµñ",
						 Font.PLAIN,
						 Size.TXT_M_S,
						 UnitDataList_CORRECT_SORT.get(i).color,
						 UnitDataList_CORRECT_SORT.get(i).unitSN + "   "+ UnitDataList_CORRECT_SORT.get(i).correctPercent ,			
						 width,	1937+90*height);//ÇÐ½ÀÈ½¼ö
				rowCount++;
				height++;
			}
			i++;
		}

		//ÀÚÁÖÆ²¸®´Â À¯Çü
		width = 1150;
		height = 0;
		rowCount = 0;
		i = 0;
		while(true){
			if(rowCount == 10){
				width = 1150 + 450;
				height = 0;
			}
			
			if(rowCount > 19 || i == UnitDataList_CORRECT_SORT.size()){
				break;
			}
			
			if(UnitDataList_CORRECT_SORT.get(i).correctPercent_double <= 50.0 && UnitDataList_CORRECT_SORT.get(i).correctPercent_double>0){
				drawString(
						_g,
						"¸¼Àº °íµñ",
						 Font.PLAIN,
						 Size.TXT_M_S,
						 UnitDataList_CORRECT_SORT.get(i).color,
						 UnitDataList_CORRECT_SORT.get(i).unitSN + "   "+ UnitDataList_CORRECT_SORT.get(i).correctPercent ,			
						 width,	1937+90*height);//ÇÐ½ÀÈ½¼ö
				rowCount++;
				height++;
			}
			i++;
		}
	}
	
	public void							drawStringAnswerSheetInfo(
			Graphics _g, 
			GetStudentData 	_gsdb ) throws IOException{

		// 
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.TestDate,						
				139				,436);
		
		
		//
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentSubject,						
				780				,436);

		//
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.GradeSemester,						
				1126			,436);	

		//
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_M,
				ColorList.BLACK,
				_gsdb.StudentName,						
				1685			,436);	
	}
	
	public void							drawStringSolutionInfo(
			Graphics _g, 
			int _questionNum, Color _questionColor,
			String _questionUnit,
			int _point_width, int _point_height) throws IOException{

		// [¹®Ç×Á¤º¸] ¹øÈ£ÀÔ·Â
		drawStringNum(
				_g,
				_questionNum,
				_questionColor,
				_point_width 	+ 10,
				_point_height 	+ 70);
		
		// [´äÁöÁ¤º¸] À¯ÇüÀÔ·Â
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_S,
				ColorList.BLACK,
				_questionUnit,						
				_point_width 	+ 130,	
				_point_height	+ 70);
		
	}
	
	

	private int gap = 0;
	public void							drawStringQuestionInfo(
			Graphics _g, 
			String _outPath,
			int _questionNum, Color _questionColor,
			String _questionType,
			int _questionLevel,
			int _incorrectCount,
			long _recentStudyPassDate,
			int _point_width, int _point_height) throws IOException{

		// [¹®Ç×Á¤º¸] ¹øÈ£ÀÔ·Â
		drawStringNum(
				_g,
				_questionNum,
				_questionColor,
				_point_width 	+ 10,
				_point_height 	+ 70);
		

		gap = 0;
		if(_questionNum > 99){
			gap = 60;
		}
		
		// [¹®Ç×Á¤º¸] ¼­¼úÇü ±¸ºÐ ÀÔ·Â
		if(_questionType.equals("¼­¼úÇü")){
			drawStringButton(
					_g,
					ColorList.BLUE_LIGHT , 		
					_questionType, 	
					_point_width  + 131 + gap, 
					_point_height + 63);
		}
		
		// [¹®Ç×Á¤º¸] ¹®Á¦ ³­ÀÌµµ ÀÔ·Â
		drawStringStar(
				_g, 
				_questionLevel, 				
				_point_width  + 131 + gap,	
				_point_height + 32);
		

		// [¹®Ç×Á¤º¸] ¹®Á¦ ¿À´ä È½¼ö ÀÔ·Â
		DI.draw_QR(_g,_outPath,_questionNum, _point_width  + Size.QR_LEFT_GAP,_point_height +  Size.QR_TOP_GAP);
		
		// [¹®Ç×Á¤º¸] ¹®Á¦ ¿À´ä È½¼ö ÀÔ·Â
		drawStringIncorrectCount(
				_g,
				_incorrectCount,										
				_point_width  + 500,	
				_point_height + 69);//36
		
		// [¹®Ç×Á¤º¸] ¹®Á¦ ÇÐ½À °æ°úÀÏ
		drawStringPastDate(
				_g,
				_recentStudyPassDate,												
				_point_width  + 610,	
				_point_height + 69);
		
	}
		
	public void 						drawStringTestPlus(Graphics _g, int _testPlus, int _x, int _y){
		String str;
		Color color;
		
		if(_testPlus > 0){
			str 						= "+"+Integer.toString(_testPlus)+"Á¡";
			color 						= ColorList.BLUE;
		}else if(_testPlus  == 0){
			str 						= "¾øÀ½";
			color 						= ColorList.BLACK;
		}else{
			str 						= Integer.toString(_testPlus)+"Á¡";
			color 						= ColorList.RED;
		}
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.PLAIN,
				Size.TXT_B_B,	
				color, 
				str,
				_x,_y);
	}
	
	public void							drawStringTestLevel(Graphics _g, String _Level){
		int width					= 0;
		if(_Level.equals("1")){
			width = 1175;
		}else if(_Level.equals("2")){
			width = 1170+217;
		}else if(_Level.equals("3")){
			width = 1160+217*2;
		}else if(_Level.equals("4")){
			width = 1165+217*3;
		}
		
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.PLAIN,
				Size.TXT_B_M,
				ColorList.BLUE,
				"¡Û",
				width,2145);
	}
	
	public void							drawStringClassReady(Graphics _g, int _ClassReady_point ){
		String str 							= "¸Å¿ì¹ÌÈí";
		Color color 						= ColorList.RED;
		if(_ClassReady_point >= 4){
			str = "¸Å¿ì¿ì¼ö";
			color = ColorList.BLUE;
		}else if(_ClassReady_point == 3){
			str = "¿ì     ¼ö";
			color = ColorList.BLUE;
		}else if(_ClassReady_point == 2){
			str = "º¸     Åë";
			color = ColorList.BLACK;
		}else if(_ClassReady_point == 1){
			str = "¹Ì     Èí";
			color = ColorList.RED;
		};
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.PLAIN,
				Size.TXT_B_B,
				color,
				str,
				420,1300);
	}
	
	public void							drawStringClassResult(Graphics _g, String _ClassResult_point ){
		String str 								="¸Å¿ì¹ÌÈí";
		int	ClassResult_point					=Integer.parseInt(_ClassResult_point);
		Color color 							=ColorList.RED;
		if(ClassResult_point >= 90){
			str = "¸Å¿ì¿ì¼ö";
			color = ColorList.BLUE;
		}else if(ClassResult_point >= 80){
			str = "¿ì     ¼ö";
			color = ColorList.BLUE;
		}else if(ClassResult_point >= 70){
			str = "º¸     Åë";
			color = ColorList.BLACK;
		}else if(ClassResult_point >= 50){
			str = "¹Ì    Èí";
			color = ColorList.RED;
		}
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.PLAIN,
				Size.TXT_B_B,
				color,
				str,
				420,1745);
	}
	
	public void							drawStringQuestionNum(Graphics _g, String _num,	Color _color, int _x, int _y){
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.PLAIN,
				Size.TXT_M_B,
				_color,
				_num,
				_x,_y);
	}

	public void							drawStringButton(Graphics _g, Color _color,String _str, int _x, int _y){
		_g.setColor(_color);
		_g.drawRoundRect(_x, _y - 23, 75, 30, 10, 10);
		drawString(
				_g,
				"¸¼Àº °íµñ",
				Font.BOLD,
				Size.TXT_S_B,
				_color, 
				_str, 
				_x+7,_y);
	}

	public void							drawStringNum(Graphics _g, int _num, Color _color, int _x, int _y){
		String out = "";
		if(_num < 10){
			out = "0"+Integer.toString(_num);
		}else{
			out = Integer.toString(_num);
		}
		drawString(_g,"¸¼Àº °íµñ",Font.PLAIN,Size.TXT_QUESTION_NUM,_color,out,_x,_y);
	}

	public void 						drawStringStar(Graphics _g, int _starCount, int _x, int _y){
		String out ="";
		for(int i = 0 ; i < _starCount; i++){
			out = out + "¡Ú";
		}
		drawString(
				_g,	
				"¸¼Àº °íµñ",	
				Font.PLAIN,	
				Size.TXT_M_S,	
				ColorList.YELLOW,	
				out,	
				_x,	_y);
	}

	public void							drawStringIncorrectCount(Graphics _g, int _incorrectCount, int _x, int _y)throws IOException{

		if(_incorrectCount != 0){
			DI.drawImg(_g,Path.INFO_COUNT_BACKGROUND(), _x, _y-22, 10*10, 3*10);
			drawString(
					_g, 
					"¸¼Àº °íµñ", 
					Font.BOLD, 
					Size.TXT_S_B, 
					ColorList.WHITE, 
					"Æ²¸²", 
					_x+5, _y);
			
			if(_incorrectCount < 10){
				drawString(
						_g, 
						"¸¼Àº °íµñ", 
						Font.PLAIN, 
						Size.TXT_S_B,
						ColorList.GRAY_DARK,
						Integer.toString(_incorrectCount)+"¹ø",
						_x+60,	_y);
			}else{
				drawString(
						_g, 
						"¸¼Àº °íµñ",
						Font.PLAIN, 
						Size.TXT_S_B,
						ColorList.GRAY_DARK,
						Integer.toString(_incorrectCount),	
						_x+60,	_y);
			}		
		}
	}

	public void							drawStringPastDate(Graphics _g,	long _recentStudyPassDate, int _x, int _y) throws IOException{
		if(_recentStudyPassDate !=  -1l){
			String out ="";
			DI.drawImg(_g,Path.INFO_COUNT_BACKGROUND(), _x, _y-22, 10*10, 3*10);
			drawString(
					_g,
					"¸¼Àº °íµñ", 
					Font.BOLD, 
					Size.TXT_S_B,
					ColorList.WHITE,
					"°æ°ú",
					_x+5,_y);
			
			if(_recentStudyPassDate != 0){
				if(_recentStudyPassDate < 7){
					out = Integer.toString((int) (_recentStudyPassDate) ) + "ÀÏ";
				}else if(_recentStudyPassDate < 7*2){
					out = "1ÁÖ";
				}else if(_recentStudyPassDate < 7*3){
					out = "2ÁÖ";
				}else if(_recentStudyPassDate < 7*4){
					out = "3ÁÖ";
				}else if(_recentStudyPassDate < 7*5){
					out = "4ÁÖ";
				}else if(_recentStudyPassDate < 7*6*2){
					out = "1´Þ";
				}else {
					out = "ÃÊ°ú";
				}
				
			}else{
				out = "¿À´Ã";
			}
			drawString(
					_g, 
					"¸¼Àº °íµñ", 
					Font.PLAIN, 
					Size.TXT_S_B,
					ColorList.GRAY_DARK,
					out,_x+60,_y);
		}
	}
	
	public void							drawStringPageNum(Graphics _g,  int _pageNum){

		int 						pageNum_point_width 	= 0 ;
		int 						pageNum_point_height 	=  Size.HEIGHT - 75 ;
		
		if(99<_pageNum){
			pageNum_point_width = Size.WIDTH/2 - 45 ;
		}else if(9<_pageNum){
			pageNum_point_width =  Size.WIDTH/2 - 30 ;
		}else{
			pageNum_point_width =  Size.WIDTH/2 - 15 ;
		}
		drawString(
				_g,
				"¸¼Àº °íµñ", 
				Font.PLAIN,
				Size.TXT_M_B,
				ColorList.BLACK,
				Integer.toString(_pageNum),
				pageNum_point_width,pageNum_point_height);
	}
	
	public void							drawString(Graphics _g, String _name, int _style, int _txtSize, Color _color, String _str, int _x, int _y){
		_g.setFont(new Font(_name,_style,_txtSize));
		_g.setColor(_color);
		_g.drawString(_str,_x,_y);
	}
}
