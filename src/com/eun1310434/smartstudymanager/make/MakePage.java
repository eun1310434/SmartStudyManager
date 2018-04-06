package com.eun1310434.smartstudymanager.make;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.eun1310434.smartstudymanager.tool.DrawImage;
import com.eun1310434.smartstudymanager.tool.DrawString;
import com.eun1310434.smartstudymanager.tool.Path;
import com.eun1310434.smartstudymanager.tool.Size;

public class MakePage {

	private DrawImage			DI					= new DrawImage();
	private DrawString			DS					= new DrawString();	
	private OutputStream 		out 				= null;
	private Graphics 			g 					= null;
	private BufferedImage 		BI			 		= new BufferedImage(Size.WIDTH, Size.HEIGHT, BufferedImage.TYPE_INT_RGB);


	public void 				makeTestCoverPage(String _name, String _outPath,String _level	,GetStudentData 	_gsdb ) throws IOException{
 		out = new FileOutputStream(_outPath+_name+".jpg");
		g 	= BI.createGraphics();
		DI.drawPage_Background(g, Path.WORKBOOK_TEST_COVER());//배경이미지 입력
		DS.drawStringTestCoverInfo(g,_level,_gsdb);	
		DI.makeFinalPrintOut(out,BI);
	}

	public void					makeStudyReadyCheck(String _name, String _outPath)   throws IOException{
		out = new FileOutputStream(_outPath+_name+".jpg");
		g 	= BI.createGraphics();
		DI.drawPage_Background(g, Path.STUDY_READY_CHECK());//배경입력	
		DI.makeFinalPrintOut(out,BI);	
	}

	public void					makeStudyResult(String _name, String _outPath,GetStudentData 	_gsdb )   throws IOException{
		out = new FileOutputStream(_outPath+_name+".jpg");
		g 	= BI.createGraphics();
		DI.drawPage_Background(g, Path.STUDY_RESULT_SHEET());//배경입력	
		DS.drawStringStudyResultInfo(g,_gsdb);	
		DI.makeFinalPrintOut(out,BI);	
	}

	public void 				makeFormulaPage_One(String _name, String _outPath, String UnitName)  throws IOException{
		out = new FileOutputStream(_outPath+_name+".jpg");
		g 	= BI.createGraphics();
		DI.drawPage_Background(g, Path.FORMULA_BACKGROUND_IMAGE());//배경입력	
		try {
			DI.drawPage_Formula(g,ImageIO.read(new File(Path.FORMULA_IMAGE(UnitName))));	
		} catch (Exception e) {
			System.out.println("MakeWorkBookPage - makeFormulaPageOne : There is No Formula Page");
			System.out.println(UnitName);
		}
		DS.drawStringFormulaInfo(g, UnitName);
		DI.makeFinalPrintOut(out,BI);
	}
	
	public void 				makeQuestionPage(String _name, String _outPath, ArrayList<QuestionInfo_AL> _questionDataList, String _oddOReven, String _type)  throws IOException{
		if(_type.equals("two")){
			makeQuestionPage_Two(_name,_outPath,_questionDataList,_oddOReven);
		}else if(_type.equals("many")){
			makeQuestionPage_Many(_name,_outPath,_questionDataList,_oddOReven);
		}
	}
	
	
	public void 				makeQuestionPage_Two(String _name, String _outPath, ArrayList<QuestionInfo_AL> _questionDataList, String _oddOReven)  throws IOException{

		int 		  pageNum 							= 0;  	

		Path.RESET_QR_FILE(_outPath);
		DI.make_QR(_outPath+"\\QR",_questionDataList);
		
		int i = 0;
		while(i < _questionDataList.size()){
			
			out = new FileOutputStream(_outPath +_name+"_" + Integer.toString(pageNum+1) + ".jpg");
			g   = BI.createGraphics();
			
			if(_questionDataList.size() == i+1){ // 1개의 문제만 남을경우
				
				DI.drawPage_Background(g, Path.WORKBOOK_BACKGROUND_SHORT_A());	
				
				/**정보 입력 left*/
				DS.drawStringQuestionInfo(
						g, 
						_outPath,
						i+1,  
						_questionDataList.get(i).recentStudyResultColor, 
						_questionDataList.get(i).questionType, 
						_questionDataList.get(i).questionLevel, 
						_questionDataList.get(i).incorrectCount_Int, 
						_questionDataList.get(i).recentStudyPassDate, 
						Size.WIDTH_GAP, Size.HEIGHT_GAP);
				
				/**문제 입력 Left*/			
				DI.drawPage_QuestionTwo(
						g,
						ImageIO.read(new File(_questionDataList.get(i).question_path)),
						Size.WIDTH_GAP,
						Size.QUESTION_TOP_GAP);
				
			}else{
				
				DI.drawPage_Background(g, Path.WORKBOOK_BACKGROUND_SHORT_B());	
				
				/**정보 입력 left*/
				DS.drawStringQuestionInfo(
						g, 
						_outPath,
						i+1,  
						_questionDataList.get(i).recentStudyResultColor, 
						_questionDataList.get(i).questionType, 
						_questionDataList.get(i).questionLevel, 
						_questionDataList.get(i).incorrectCount_Int, 
						_questionDataList.get(i).recentStudyPassDate, 
						Size.WIDTH_GAP, Size.HEIGHT_GAP);
				
				/**문제 입력 Left*/			
				DI.drawPage_QuestionTwo(
						g,
						ImageIO.read(new File(_questionDataList.get(i).question_path)),
						Size.WIDTH_GAP,
						Size.QUESTION_TOP_GAP);
				
				
				/**정보 입력 Right*/
				DS.drawStringQuestionInfo(
						g, 
						_outPath,
						i+2,  
						_questionDataList.get(i+1).recentStudyResultColor, 
						_questionDataList.get(i+1).questionType, 
						_questionDataList.get(i+1).questionLevel, 
						_questionDataList.get(i+1).incorrectCount_Int, 
						_questionDataList.get(i+1).recentStudyPassDate, 
						Size.WIDTH/2+26, Size.HEIGHT_GAP);

				/**문제 입력 Right*/			
				DI.drawPage_QuestionTwo(
						g,
						ImageIO.read(new File(_questionDataList.get(i+1).question_path)),
						Size.WIDTH/2+26,
						Size.QUESTION_TOP_GAP);
			}
			

			pageNum = pageNum + 1;
			i 		= i + 2;
			
			//페이지 번호
			DS.drawStringPageNum(g,pageNum);		

			//출력 
			DI.makeFinalPrintOut(out,BI);
		}

		drawEmptyPage(pageNum,_name, _outPath, _oddOReven); //빈 페이지 만들기
		Path.DELETE_QR_FILE(_outPath);
		
	}
	
	public void 				makeQuestionPage_Many(String _name, String _outPath, ArrayList<QuestionInfo_AL> _questionDataList, String _oddOReven)  throws IOException{

		BufferedImage BI_test						= null;
		
		int pageNum 								= 0;
		int point_width  							= 0;
		int point_height  							= 0;
		int page_part_question_count				= 0;

		Path.RESET_QR_FILE(_outPath);
		DI.make_QR(_outPath+"\\QR",_questionDataList);
		
		int i = 0 ;
		while(true){
			if(i == _questionDataList.size()){break;}
			
			//페이지번호를 증가 시킨다.
			pageNum = pageNum + 1;
			
			//처음 입력 포인트를 설정
			point_width 	= Size.QUESTION_MANY_START_POINT_WIDTH_LEFT;
			point_height 	= Size.QUESTION_MANY_START_POINT_HEIGHT;
			
			// 출력
			out = new FileOutputStream(_outPath +_name+"_" + Integer.toString(pageNum) + ".jpg");
			g 	= BI.createGraphics();

			// 배경 입력
			DI.drawPage_Background(g,Path.TEST_BACKGROUND_IMAGE());
						
			// 한페이지 안에서 출력
			while(true){
				if(i == _questionDataList.size()){break;}
				BI_test = ImageIO.read(new File(_questionDataList.get(i).question_path));

				
				//문제집 구성은 2단으로 되어 있으며 높이를 넘기면 1단에서 2단으로 넘어간다.
				if(point_height 
						+ Size.QUESTION_MANY_INFO_HEIGHT
						+ BI_test.getHeight()*(Size.WIDTH/2 - Size.QUESTION_MANY_START_POINT_WIDTH_LEFT)/BI_test.getWidth()
						+ Size.QUESTION_MANY_QUSTION_BETWEEN_GAP_TOP
						> Size.HEIGHT - Size.QUESTION_MANY_GAP_BOTTOM 
						
						&&
						
						page_part_question_count > 0){
					//시험지 오른쪽 칸 채우기 && 현제 높이 포인트 + 시험지 정보입력란 높이 + 시험지  높이
					
					if(point_width == Size.QUESTION_MANY_START_POINT_WIDTH_RIGHT ){
						
						page_part_question_count 	= 0;
						break;
					}else if(point_width == Size.QUESTION_MANY_START_POINT_WIDTH_LEFT ){
						
						point_width 				= Size.QUESTION_MANY_START_POINT_WIDTH_RIGHT;
						point_height 				= Size.QUESTION_MANY_START_POINT_HEIGHT;
						page_part_question_count 	= 0;
					}
				}
				
				// 한쪽 페이지에 문제 1개 이상은 입력을 해야한다.
				page_part_question_count++;
				
				// 문제의 정보를 입력한다.
				DS.drawStringQuestionInfo(
						g,
						_outPath,
						i+1,_questionDataList.get(i).recentStudyResultColor,
						_questionDataList.get(i).questionType,
						_questionDataList.get(i).questionLevel,
						_questionDataList.get(i).incorrectCount_Int,
						_questionDataList.get(i).recentStudyPassDate,
						point_width,point_height);

				
				// [문항정보] 높이입력
				point_height = point_height + Size.QUESTION_MANY_INFO_HEIGHT;
				

				// [문제이미지] 이미지
				point_height = DI.drawPage_QuestionMany(g,BI_test,point_width,point_height);
				
				i++;
			}
			
			//페이지 번호
			DS.drawStringPageNum(g,pageNum);		

			//출력 
			DI.makeFinalPrintOut(out,BI);
		}

		drawEmptyPage(pageNum,_name, _outPath, _oddOReven); //빈 페이지 만들기
		Path.DELETE_QR_FILE(_outPath);
	}
	
	public void 				makeSolutionPage_Many(String _name ,String _outPath,ArrayList<QuestionInfo_AL> _questionDataList, String _oddOReven )  throws IOException{

		BufferedImage BI_solution 			= null;
		int pageNum 						= 0;
		int point_width  					= 0;
		int point_height  					= 0;
		int page_part_solution_count		= 0;
		
		
		int i 								= 0;
		while(true){
			if(i == _questionDataList.size()){break;}

			//페이지번호를 증가 시킨다.
			pageNum = pageNum + 1;

			//처음 입력 포인트를 설정
			point_width 	= Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT;
			point_height 	= Size.SOLUTION_MANY_START_POINT_HEIGHT;

			// 출력
			out = new FileOutputStream(_outPath +_name+"_" + Integer.toString(pageNum) + ".jpg");
			g = BI.createGraphics();
			
			// 배경 입력
			DI.drawPage_Background(g,Path.SOLUTION_BACKGROUND_IMAGE());


			// 한페이지 안에서 출력
			while(true){
				if(i == _questionDataList.size()){break;}
				
				
				// [데이터] 답지
				BI_solution = ImageIO.read(new File(_questionDataList.get(i).solution_path));

				if(point_height 
						+ Size.SOLUTION_MANY_INFO_HEIGHT
						+ BI_solution.getHeight()*(Size.WIDTH/2 - Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT)/BI_solution.getWidth()
						> Size.HEIGHT - Size.SOLUTION_MANY_GAP_BOTTOM 
						
						&&
						
						page_part_solution_count > 0){
					//답지 오른쪽 칸 채우기 && 현제 높이 포인트 + 답지 정보입력란 높이 + 시험지  높이
					
					if(point_width == Size.SOLUTION_MANY_START_POINT_WIDTH_RIGHT ){
						page_part_solution_count 	= 0;
						break;
					}else if(point_width == Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT ){
						
						point_width 				= Size.SOLUTION_MANY_START_POINT_WIDTH_RIGHT;
						point_height 				= Size.SOLUTION_MANY_START_POINT_HEIGHT;
						page_part_solution_count 	= 0;
					}
				}
				
				
				// 한쪽 페이지에 답지 1개 이상은 입력을 해야한다.
				page_part_solution_count++;

				// 문제의 정보를 입력한다.
				DS.drawStringSolutionInfo(
						g,
						i+1,_questionDataList.get(i).recentStudyResultColor,
						_questionDataList.get(i).questionUnit,
						point_width,point_height);


				// [답지정보] 높이입력
				point_height = point_height + Size.SOLUTION_MANY_INFO_HEIGHT;
				

				// [답지이미지] 이미지
				point_height = DI.drawPage_SolutionMany(g,BI_solution,point_width,point_height);
				
				i++;
			}
			
			//페이지 번호
			DS.drawStringPageNum(g,pageNum);		
			

			//[출력] 
			DI.makeFinalPrintOut(out,BI);
		}

		drawEmptyPage(pageNum,_name, _outPath, _oddOReven); //빈 페이지 만들기
	}

	public void					makeAnswerSheetPage(String _name, String _outPath ,ArrayList<QuestionInfo_AL> _questionDataList, String _oddOReven,GetStudentData 	_gsdb  ) throws IOException{

		/**앞페이지*/
		BufferedImage BI_answersheet				= null;
		
		int pageNum = 0;
		int start_point_height  	= 0;
		int point_width  			= 0;
		int point_height  			= 0;
		
		int i = 0 ;
		while(true){
			if(i == _questionDataList.size()){break;}
			
			pageNum = pageNum + 1;
			
			out = new FileOutputStream(_outPath +_name+"_" + Integer.toString(pageNum) + ".jpg");
			g = BI.createGraphics();
			
			
			// [입력] 답지 배경 이미지 입력 
			if(pageNum == 1){
				// 배경 입력
				DI.drawPage_Background(g,Path.ANSWER_SHEET_BACKGROUND_A());
				
				//[2] 정보 입력
				DS.drawStringAnswerSheetInfo(g,_gsdb);
				start_point_height = Size.ANSWERSHEET_START_POINT_HEIGHT_FIRST;
			}else{
				// 배경 입력
				DI.drawPage_Background(g,Path.ANSWER_SHEET_BACKGROUND_B());
				start_point_height = Size.ANSWERSHEET_START_POINT_HEIGHT_SECOND;
			}
			
			point_width 	= Size.ANSWERSHEET_START_POINT_WIDTH_LEFT;
			point_height 	= start_point_height;
			
			while(true){
				if(i == _questionDataList.size()){break;}

				if(_questionDataList.get(i).questionType.equals("객관식")){
					BI_answersheet = ImageIO.read(new File(Path.ANSWER_SHEET_MC()));
				}else if(_questionDataList.get(i).questionType.equals("서술형")){
					BI_answersheet = ImageIO.read(new File(Path.ANSWER_SHEET_BD()));
				}else{
					BI_answersheet = ImageIO.read(new File(Path.ANSWER_SHEET_SA()));
				}
				
				if(point_width == Size.ANSWERSHEET_START_POINT_WIDTH_RIGHT 
						
						&& 
						
						point_height + 
						BI_answersheet.getHeight()*(Size.WIDTH/2 - Size.ANSWERSHEET_START_POINT_WIDTH_LEFT)/BI_answersheet.getWidth()
						
						> Size.ANSWERSHEET_GAP_BOTTOM ){
					//시험지 오른쪽 칸 채우기 && 현제 높이 포인트 + 시험지 정보입력란 높이 + 시험지  높이
					break;
				}else if(point_width == Size.ANSWERSHEET_START_POINT_WIDTH_LEFT 
						
						&& 
						
						point_height + 
						BI_answersheet.getHeight()*(Size.WIDTH/2 - Size.ANSWERSHEET_START_POINT_WIDTH_LEFT)/BI_answersheet.getWidth()
						
						> Size.ANSWERSHEET_GAP_BOTTOM ){
					//시험지 왼쪽 칸 채우기 && 현제 높이 포인트 + 시험지 정보입력란 높이 + 시험지  높이
					point_width 	= Size.ANSWERSHEET_START_POINT_WIDTH_RIGHT ;
					point_height 	= start_point_height;
				}

				// [입력] 답지 이미지 입력
				DI.drawPage_AnswerSheet(
						g, 
						BI_answersheet, 
						point_width, 
						point_height);

				DS.drawStringNum(
						g, 
						i+1, 
						_questionDataList.get(i).recentStudyResultColor,
						point_width + 20,	
						point_height + 80);
				
				// [계산] 문제 높이
				point_height =   
						point_height + 
						BI_answersheet.getHeight()*(Size.WIDTH/2 - Size.ANSWERSHEET_START_POINT_WIDTH_LEFT)/BI_answersheet.getWidth()
						-1;
				i++;
			}
			
			//페이지 번호
			DS.drawStringPageNum(g,pageNum);		
			

			//[출력] 
			DI.makeFinalPrintOut(out,BI);
		}
		drawEmptyPage(pageNum,_name, _outPath, _oddOReven); //빈 페이지 만들기
	}
		
	public void					drawEmptyPage(int _pageNum ,String _name, String _outPath, String oddOReven) throws IOException{
		

		if( (_pageNum%2 == 1 && oddOReven.equals("even")) || (_pageNum%2 == 0 && oddOReven.equals("odd"))){

			_pageNum++;
			
			out = new FileOutputStream(_outPath +_name+"_" + Integer.toString(_pageNum) + ".jpg");
			g = BI.createGraphics();
			
			/**이미지 입력*/
			DI.drawPage_Empty(g);				//[1] A4페이지 입력			
			DS.drawStringPageNum(g,_pageNum);	//[2] 페이지 번호
			
			/**출력*/
			DI.makeFinalPrintOut(out,BI);
		
		}
	}
	
}
