package com.eun1310434.smartstudymanager.make;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;


public class MyNoteCutter {
	
	public void getFileName(String _Path, ArrayList<String> _FileNameList) {
		String FileName = "";
		File dir = new File(_Path); 
		File[] fileList = dir.listFiles(); 
		for(int i = 0 ; i < fileList.length ; i++){
			if(fileList[i].isFile()){
				FileName = fileList[i].getName();
				if(FileName.contains(".jpg")){
					_FileNameList.add(FileName);	
				}
			}
		}
	}
	
	public void getSN(String _dbPath, String _name, ArrayList<String> _CutMakeQuestion_SN) {
    	BufferedReader br 		= null;
    	String  lineRead 		= null;
    	
    	try {
    		//파일을 읽어 옵니다.
			br = new BufferedReader(new FileReader(_dbPath+"\\"+_name+".txt"));
			
			//파일내용을 한줄 한줄 저장합니다.
			while ((lineRead = br.readLine()) != null) {
				_CutMakeQuestion_SN.add(lineRead);
			}
	        br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setCut_Left(String _InputPath, String _InfileName,String _OutputPath, String _OutfileName) throws IOException{
		setCut(1,_InputPath,_InfileName,_OutputPath,_OutfileName);
	}
	
	public void setCut_Right(String _InputPath, String _InfileName,String _OutputPath, String _OutfileName) throws IOException{
		setCut(2,_InputPath,_InfileName,_OutputPath,_OutfileName);
	}
	
	public void setCut(int _type, String _InputPath, String _InfileName,String _OutputPath, String _OutfileName) throws IOException{
		//[1] 이미지갖고오기
		File 		  	file 		= new File(_InputPath+"\\"+_InfileName);
		BufferedImage 	bi 			= ImageIO.read(file);

		int pageHeight				= 2970;
		int pageWidth				= 2100;
		
		int StartPoint_Width_left 	= 107;
		int StartPoint_Width_right 	= 1052;
		int StartPoint_Height 		= 230;
		int ImageWidth 				= 937;
		//int ImageHeight 			= 2732-StartPoint_Height;
		int ImageHeight 			= 2560-StartPoint_Height;
		
	
		StartPoint_Width_left 		= (bi.getWidth()	*	StartPoint_Width_left)	/pageWidth;
		StartPoint_Width_right 		= (bi.getWidth()	*	StartPoint_Width_right)	/pageWidth;
		StartPoint_Height 			= (bi.getHeight()	*	StartPoint_Height)		/pageHeight;
		ImageWidth 					= (bi.getWidth()	*	ImageWidth)				/pageWidth;
		ImageHeight 				= (bi.getHeight()	*	ImageHeight)			/pageHeight;
		
		
		BufferedImage 	bi_out 		= new BufferedImage(ImageWidth,ImageHeight, BufferedImage.TYPE_INT_RGB);
		
		
		OutputStream out 			= new FileOutputStream(_OutputPath+"\\"+_OutfileName+".jpg");
		Iterator 	imageWriters 	= null; // [선언]
		ImageWriter imgWriter 		= null; // [선언]
		Graphics 	g 				= bi_out.getGraphics();
		
		//[2] 이미지수정
		if(_type == 1){
			g.drawImage(bi.getSubimage(StartPoint_Width_left,StartPoint_Height,ImageWidth,ImageHeight), 0,0,ImageWidth,ImageHeight,null); 	
		}else if(_type == 2){
			g.drawImage(bi.getSubimage(StartPoint_Width_right,StartPoint_Height,ImageWidth,ImageHeight), 0,0,ImageWidth,ImageHeight,null); 
	 			
		}
		//[3] 출력
		imageWriters = ImageIO.getImageWritersBySuffix("jpg"); 
		while(imageWriters.hasNext()){
			imgWriter = (ImageWriter)imageWriters.next(); 
		} 
		ImageIO.write(bi_out, "jpg", out);
	}
}
