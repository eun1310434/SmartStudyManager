package com.eun1310434.smartstudymanager.tool;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.eun1310434.smartstudymanager.make.QR_TOOL;
import com.eun1310434.smartstudymanager.make.QuestionInfo_AL;
import com.google.zxing.WriterException;

public class DrawImage {	
	private Iterator 			imageWriters 		= null; 
	private ImageWriter 		imgWriter 			= null;
	

	public void						drawPage_AnswerSheet(Graphics _g, BufferedImage _bi, int _width, int _height){
		_g.drawImage(
				_bi, 	
				_width, 
				_height , 
				Size.WIDTH/2 - Size.ANSWERSHEET_START_POINT_WIDTH_LEFT,
				_bi.getHeight()*(Size.WIDTH/2 - Size.ANSWERSHEET_START_POINT_WIDTH_LEFT)/_bi.getWidth(),
				null);//이미지 그리기
	}

	public int						drawPage_SolutionMany(Graphics _g, BufferedImage _bi, int _width, int _height){
		// [답지이미지] 이미지
		if(_bi.getHeight()*(Size.WIDTH/2 - Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth() > Size.SOLUTION_MANY_SOLUTION_HEIGHT){

			_g.drawImage(
					_bi, 	
					_width, 
					_height , 
					_bi.getWidth()*Size.SOLUTION_MANY_SOLUTION_HEIGHT/_bi.getHeight(),
					Size.SOLUTION_MANY_SOLUTION_HEIGHT,
					null);//이미지 그리기
			// [답지이미지] 높이계산
			return  Size.SOLUTION_MANY_SOLUTION_BETWEEN_GAP_TOP + _height + Size.SOLUTION_MANY_SOLUTION_HEIGHT;
		}else{
			
			_g.drawImage(
					_bi, 	
					_width, 
					_height , 
					Size.WIDTH/2 - Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT,
					_bi.getHeight()*(Size.WIDTH/2 - Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth(),
					null);//이미지 그리기
			// [답지이미지] 높이계산
			return  Size.SOLUTION_MANY_SOLUTION_BETWEEN_GAP_TOP + _height + 
					_bi.getHeight()*(Size.WIDTH/2 - Size.SOLUTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth();
		}
		
	}
	
public void							make_QR(String _outPath,	ArrayList<QuestionInfo_AL> _questionDataList){
		
		QR_TOOL qr 	= new QR_TOOL();
		String data = "";
		for(int i = 0 ; i < _questionDataList.size(); i++){
			try {
				//data = gsdb.getTest_sn()+_questionDataList.get(i).questionSN;
				data = _questionDataList.get(i).questionSN;
				qr.createQRCode(data, _outPath+"\\"+Integer.toString(i+1)+".jpg");
			} catch (WriterException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

	public void						draw_QR(Graphics _g,String _path ,int _number,  int _width, int _height) throws IOException{
		//System.out.println(Path.QR_RESOURCE(_path,_number));
		drawImg(_g, Path.QR_RESOURCE(_path,_number), _width, _height, Size.QR_WIDTH, Size.QR_HEIGHT);
	}
	
	public void						drawPage_QuestionTwo(Graphics _g, BufferedImage _bi, int _width, int _height){
		if(_bi.getHeight()*( Size.WIDTH/2 - Size.WIDTH_GAP - 20)/_bi.getWidth() >  Size.QUESTION_TWO_HEIGHT){
			_g.drawImage(
					_bi, 
					_width, 
					_height,  
					_bi.getWidth()*Size.QUESTION_TWO_HEIGHT/_bi.getHeight(), 
					Size.QUESTION_TWO_HEIGHT,
					null);
		}else {
			_g.drawImage(
					_bi, 
					_width, 
					_height,  
					Size.WIDTH/2 - Size.WIDTH_GAP - 20, 
					_bi.getHeight()*( Size.WIDTH/2 - Size.WIDTH_GAP - 20)/_bi.getWidth(),
					null);
		}
	}
		
	public int						drawPage_QuestionMany(Graphics _g, BufferedImage _bi, int _width, int _height){
		if(_bi.getHeight()*(Size.WIDTH/2 - Size.QUESTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth() > Size.QUESTION_MANY_QUESTION_HEIGHT){

			_g.drawImage(
					_bi, 	
					_width, 
					_height , 
					_bi.getWidth()*Size.QUESTION_MANY_QUESTION_HEIGHT/_bi.getHeight(),
					Size.QUESTION_MANY_QUESTION_HEIGHT,
					null);//이미지 그리기
			// [문제이미지] 높이계산
			return Size.QUESTION_MANY_QUSTION_BETWEEN_GAP_TOP + _height + Size.QUESTION_MANY_QUESTION_HEIGHT;
		}else{
			
			_g.drawImage(
					_bi, 	
					_width, 
					_height , 
					Size.WIDTH/2 - Size.QUESTION_MANY_START_POINT_WIDTH_LEFT,
					_bi.getHeight()*(Size.WIDTH/2 - Size.QUESTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth(),
					null);//이미지 그리기
			// [문제이미지] 높이계산
			return Size.QUESTION_MANY_QUSTION_BETWEEN_GAP_TOP + _height + 
					_bi.getHeight()*(Size.WIDTH/2 - Size.QUESTION_MANY_START_POINT_WIDTH_LEFT)/_bi.getWidth();
		}
	}
	
	public void						drawPage_Formula(Graphics _g, BufferedImage _bi){

		if(_bi.getHeight() * (Size.WIDTH - Size.WIDTH_GAP*2)/_bi.getWidth() > Size.FORMULA_HEIGHT){
			_g.drawImage(
					_bi, 
					Size.WIDTH_GAP,  
					Size.FORMULA_GAP_TOP,  
					_bi.getWidth()*Size.FORMULA_HEIGHT/_bi.getHeight(), 
					Size.FORMULA_HEIGHT,
					null);
			
		}else {
			_g.drawImage(
					_bi, 
					Size.WIDTH_GAP, 
					Size.FORMULA_GAP_TOP,  
					Size.WIDTH - Size.WIDTH_GAP*2, 
					_bi.getHeight() * (Size.WIDTH - Size.WIDTH_GAP*2)/_bi.getWidth(),
					null);
		}
	}

	public void						makeFinalPrintOut(OutputStream 	_out,BufferedImage _bi) throws IOException{
		imageWriters = ImageIO.getImageWritersBySuffix("jpg"); 
		
		while(imageWriters.hasNext()){
			imgWriter = (ImageWriter)imageWriters.next(); 
		} 
		
		ImageIO.write(_bi, "jpg", _out);
	}

	public void						drawPage_Background(Graphics _g, String _path) throws IOException{
		drawImg(_g,_path,0,0, Size.WIDTH, Size.HEIGHT);
	}
	
	public void						drawPage_Empty(Graphics _g) throws IOException{
		_g.fillRect(0, 0, Size.WIDTH,Size.HEIGHT); 
		_g.setColor(ColorList.WHITE);
	}
	

	public void						drawImg(Graphics _g, String _path, int _x, int _y, int _width, int _height) throws IOException{
		_g.drawImage(ImageIO.read(new File(_path)), _x, _y, _width, _height, null);
	}
	
	
}
