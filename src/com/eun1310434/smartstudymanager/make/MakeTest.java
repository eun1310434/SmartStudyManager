package com.eun1310434.smartstudymanager.make;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;

import com.eun1310434.smartstudymanager.tool.ColorList;
import com.eun1310434.smartstudymanager.tool.Path;
import com.eun1310434.smartstudymanager.tool.Size;

public class MakeTest {
	
	private MakePage    		PAGE				= new MakePage();
	
	private OutputStream 		out 				= null;	
	private Iterator 			imageWriters 		= null; 
	private ImageWriter 		imgWriter 			= null;
	private Graphics 			g 					= null;
	private BufferedImage 		BI_background 		= new BufferedImage(Size.WIDTH, Size.HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GetStudentData 		gsdb 				= null; 


	public  					MakeTest(
			String _StudentName,
			String _StudentSubject,
			String _StudentGrade,
			String _StudentSemester,
			String _StudentTest,
			String _TestDate
			) throws Exception{

		String db_name				=  	_StudentName+"_"+_StudentSubject+"_"+_StudentGrade+"_"+_StudentSemester+"_"+_StudentTest+".xlsx";
		gsdb = new GetStudentData(
				Path.DB_STUDENT(_StudentName,db_name),
				_StudentName,
				_StudentSubject,
				_StudentGrade,
				_StudentSemester,
				_StudentTest,
				_TestDate
				);
	 }
	
	
	
	
	public void 						Test(String _testLevel, int _testCount,	int _questionCount)	throws Exception{
		//��
		Path.RESET_TEST_FILE(gsdb.StudentName, gsdb.ClassName, gsdb.StudentSubject);
		for(int i = 0 ; i < _testCount ; i++){
			Test(_testLevel, i+1,gsdb.getRandomQuestionDataList(
					_testLevel,
					_questionCount, 			
					gsdb.getStudyQuestionDataList()));
		}
	}
	

	private void 						Test(String _testLevel, int _testCount,	ArrayList<QuestionInfo_AL> _questionDataList)	throws Exception{
		String path = Path.TEST(gsdb.StudentName, gsdb.ClassName, gsdb.StudentSubject);
		PAGE.makeTestCoverPage  	("T"+Integer.toString(_testCount)+"_A��"+Integer.toString(_testCount) 		,path,	_testLevel, gsdb);
		//PAGE.makeStudyReadyCheck	("T"+Integer.toString(_testCount)+"_B������"+Integer.toString(_testCount)	,path);
		PAGE.makeStudyResult		("T"+Integer.toString(_testCount)+"_B�н����"+Integer.toString(_testCount)	,path, gsdb);
		PAGE.makeQuestionPage_Many	("T"+Integer.toString(_testCount)+"_C����"+Integer.toString(_testCount)		,path,	_questionDataList,	"even");
		PAGE.makeAnswerSheetPage  	("T"+Integer.toString(_testCount)+"_D�����"+Integer.toString(_testCount)		,path,	_questionDataList,	"even", gsdb);
		PAGE.makeSolutionPage_Many	("T"+Integer.toString(_testCount)+"_E�ش���"+Integer.toString(_testCount)		,path,	_questionDataList,	"even");
		makeQuestionData			("T"+Integer.toString(_testCount)											,path,	_questionDataList);
	}
	

	public void 						Result() 	throws Exception{
		//�����
		
		//gsdb.UnitDataListPrint();
		
		/*
		Path.RESET_RESULT_FILE(gsdb.getStudentName(), gsdb.getClassName(), gsdb.getStudentSubject());
		String path = Path.RESULT(gsdb.getStudentName(), gsdb.getClassName(),gsdb.getStudentSubject());
		makeStudyResultPage  		("A���" 		,path,	gsdb.getStudyQuestionDataList());
		makeQuestionInsertData		(path);
		*/
		
	}

	
	
	private String 				Cut_ParentPath 	= "";	
	private String 				Cut_InputPath  	= "";	
	private String 				Cut_OutputPath 	= "";
	private ArrayList<String> 	Cut_getFileName = null;
	private ArrayList<String> 	Cut_getSN 		= null;
	private MyNoteCutter 		cut 			= null;
	public void 						Cut(String _InputFileName, String _OutputFileName) 	throws Exception{
		/*
		 * ��     �� : ��ĵ�̹��� �ڵ� ���� �� ����
		 * �� �� �� : 17. 6.23.(��) 
		 */
		Cut_getFileName = new ArrayList<String>();	// ��ĵ�ڷ� Name List
		Cut_getSN 		= new ArrayList<String>();  // �����ڷ� SN List
		cut 			= new MyNoteCutter();		// �̹��� ���� Ŭ���� 
		
		Cut_ParentPath 	= "C:\\Users\\ddil\\Desktop\\"; 		// ����ȭ���� ������ �������� ��´�.
		Cut_InputPath  	= Cut_ParentPath+_InputFileName+"\\";	// ��ĵ�ڷ� ���� ��
		Cut_OutputPath  = Cut_ParentPath+_OutputFileName+"\\";	// �����ڷ� ���� ��
		
		Path.reMakeFolder(Cut_OutputPath); // �����ڷ� ���� ����		
		cut.getSN(		Cut_InputPath,	"Study_SN",		Cut_getSN); // �����ڷ� SN �������
		cut.getFileName(Cut_InputPath,	Cut_getFileName	);// ��ĵ�ڷ� Name �������
		
		if(Cut_getFileName.size() == 0){
			System.out.println("��ĵ������ �����ϴ�.");
		}else if(Cut_getFileName.size()*2 == Cut_getSN.size() || Cut_getFileName.size()*2 - 1 == Cut_getSN.size()){
			for(int i = 0 ; i < Cut_getFileName.size() ; i++){
				cut.setCut_Left(Cut_InputPath,	Cut_getFileName.get(i),		Cut_OutputPath,	Cut_getSN.get(i*2 + 0));//�����̹��� CUT
				cut.setCut_Right(Cut_InputPath,	Cut_getFileName.get(i),		Cut_OutputPath,	Cut_getSN.get(i*2 + 1));//�������̹��� CUT
			}
		}else{
			System.out.println("��ĵ������ �����մϴ�.");
		}
	}
	
	private void						makeFinalPrintOut(OutputStream 	out) throws IOException{
		imageWriters = ImageIO.getImageWritersBySuffix("jpg"); 
		while(imageWriters.hasNext()){
			imgWriter = (ImageWriter)imageWriters.next(); 
		} 
		ImageIO.write(BI_background, "jpg", out);
	}


 	private void 						makeCoverPage(String _name, String _outPath) throws IOException{
		/** Ŀ�������� */

		//[1] ����
 		out = new FileOutputStream(_outPath+_name+".jpg");
		g 	= BI_background.createGraphics();
		
		//[2] �Է�														//A4������ �Է�
		drawPage(g,	Path.WORKBOOK_COVER());	   									//����̹��� �Է�
		drawStringBlack_m_m(g,gsdb.TestDate,					940,2379); 	// ��¥
		drawStringBlack_m_m(g,gsdb.StudentSubject,				940,2475); 	// ����
		drawStringBlack_m_m(g,gsdb.GradeSemester,				940,2572); 	// �б�
		drawStringBlack_m_m(g,gsdb.StudentName,					940,2673); 	// �̸�
		
		//[3] ���
		makeFinalPrintOut(out);
	}
 		

	
	private void 						makePlanPage(String _purpose, String _outPath,	ArrayList<QuestionInfo_AL> _questionDataList) throws IOException{	
		/** �н���ȹ ������*/
		
		//[1] ����
 		out = new FileOutputStream(_outPath+_purpose+".jpg");
		g = BI_background.createGraphics();
		
		//[2] �Է�
		drawPage(g,Path.STUDY_PLAN_SHEET());											//����̹��� �Է�

		drawStringBlack_b_m(g,gsdb.GradeSemester,							500 ,685);	//�г�_�б�
		drawStringBlack_b_s(g,gsdb.TestDate,								110 ,881);	//��¥
		drawStringBlack_b_s(g,gsdb.StudentSubject,							992 ,881);	//����
		drawStringBlack_b_s(g,gsdb.StudentName,								1577,881);	//�̸�
		
		drawStringBlack_b_s(g,_questionDataList.size()+"��",					1830,1729);	//������

		/*
		drawStringTestPlus(g,gsdb.Test_beforepluse,					553,2500);	//���� ��������
		drawStringTestLevel(g,gsdb.Test_level);									//���� �н��ܰ�
		drawStringBlack_b_s(g,gsdb.Test_point+"��",						1293,2445);	//���� ����
		drawStringBlack_b_s(g,gsdb.Test_correctPercent+"%",			1731,2445);	//���� �����
		drawStringBlack_b_s(g,gsdb.Test_studyQcount+"��",				1293,2747);	//���� �н����׼�
		drawStringBlack_b_s(g,gsdb.Test_totalQcount+"��",				1731,2747);	//���� ��ü���׼�
		 */
		//[3] ���
		makeFinalPrintOut(out);
	}
	
	private void 						makeStudyResultPage(String _purpose, String _outPath,	ArrayList<QuestionInfo_AL> _questionDataList) throws IOException{	
		/*
		//[1] ����������
		out = new FileOutputStream(_outPath+_purpose+".jpg");
		g = BI_background.createGraphics();
		
		//[2] �������Է�
		drawPage(g,Path.STUDY_RESULT_SHEET());	//����̹��� �Է�

		drawStringBlack_b_m(g,gsdb.getGradeSemester(),					500 	,685);	//�г�_�б�
		drawStringBlack_b_s(g,gsdb.getTestDate(),						110 	,881);	//��¥
		drawStringBlack_b_s(g,gsdb.getStudentSubject(),					992 	,881);	//����
		drawStringBlack_b_s(g,gsdb.getStudentName(),					1577	,881);	//�̸�
		
		drawStringClassReady(g,gsdb.getClass_Ready_point());						//�н��غ���
		drawStringBlack_b_s(g,gsdb.getClass_emotion(),					1230,1267);	//�н��غ�_����
		drawStringBlack_b_s(g,gsdb.getClass_health(),					1436,1267);	//�н��غ�_ü��
		drawStringBlack_b_s(g,gsdb.getClass_environment(),				1642,1267);	//�н��غ�_ȯ��
		drawStringBlack_b_s(g,gsdb.getClass_rule(),						1848,1267);	//�н��غ�_ȯ��
		
		drawStringClassResult(g,gsdb.getGradingPoint(_questionDataList));						//�н������
		
		drawStringBlack_b_s(g,gsdb.getGradingPoint(_questionDataList)+"��",													1200, 1729);	//��������
		drawStringBlack_b_s(g,Integer.toString(gsdb.getCorrectCount(_questionDataList))+"��",								1410, 1729);	//�����
		drawStringBlack_b_s(g,Integer.toString(_questionDataList.size() - gsdb.getCorrectCount(_questionDataList))+"��",		1620, 1729);	//�����
		drawStringBlack_b_s(g,_questionDataList.size()+"��",																	1830, 1729);	//���׼�
			

		drawStringTestPlus(g,gsdb.getTest_pluse(),						517,2469);	//���� ��������
		drawStringTestLevel(g,gsdb.getTest_level());								//���� �н��ܰ�
		drawStringBlack_b_s(g,gsdb.getTest_point()+"��",					1293,2445);	//��������
		drawStringBlack_b_s(g,gsdb.getTest_correctPercent()+"%",		1731,2445);	//���� �����
		drawStringBlack_b_s(g, gsdb.getTest_studyQcount()+"��",			1293,2747);	//���� �н����׼�
		drawStringBlack_b_s(g,gsdb.getTest_totalQcount()+"��",			1731,2747);	//���� ��ü���׼�

		//[3] ���
		makeFinalPrintOut(out);
		*/
	}

	private void						makeQuestionData(String _name, String _outPath, ArrayList<QuestionInfo_AL> _questionDataList){		
		makeQuestionData_SN(_name+"_SN",_outPath,_questionDataList);
		makeQuestionData_Path(_name+"_Path",_outPath,_questionDataList);
	}
	
	private void						makeQuestionData_SN(String _name,	String _outPath, ArrayList<QuestionInfo_AL> _questionDataList){

		//���� SN ����, ���� �̹��� ��ġ����
		String sn = "";
		for(int i = 0; i < _questionDataList.size() ; i++){
			sn = sn +_questionDataList.get(i).questionSN+"\r\n";
		}
		makeNote(_outPath,_name,sn);
	}

	private void						makeQuestionData_Path(String _name,	String _outPath,  ArrayList<QuestionInfo_AL> _questionDataList){

		//���� SN ����, ���� �̹��� ��ġ����
		String path = "";
		for(int i = 0; i < _questionDataList.size() ; i++){
			path = path + _questionDataList.get(i).question_path+"\r\n";
		}
		makeNote(_outPath,_name,path);
	}
	
	private void 						makeNote(String _outPath, String _name, String _txt){
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(_outPath+_name+".txt"));
			bw.write(_txt);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void						drawPage(Graphics _g, String _path) throws IOException{
		drawImg(_g,_path,0,0, Size.WIDTH, Size.HEIGHT);
	}
	
	
	private void 						drawStringTestPlus(Graphics _g, int _testPlus, int _x, int _y){
		String str;
		Color color;
		
		if(_testPlus > 0){
			str 						= "+"+Integer.toString(_testPlus)+"��";
			color 						= ColorList.BLUE;
		}else if(_testPlus  == 0){
			str 						= "����";
			color 						= ColorList.BLACK;
		}else{
			str 						= Integer.toString(_testPlus)+"��";
			color 						= ColorList.RED;
		}
		
		drawString(_g,Size.TXT_B_B,	color, str,_x,_y);
	}
	
	private void						drawStringTestLevel(Graphics _g, String _Level){
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
		
		drawString(_g,Size.TXT_B_M,ColorList.BLUE,"��",width,2145);
	}
	
	private void						drawStringClassReady(Graphics _g, int _ClassReady_point ){
		String str 							= "�ſ����";
		Color color 						= ColorList.RED;
		if(_ClassReady_point >= 4){
			str = "�ſ���";
			color = ColorList.BLUE;
		}else if(_ClassReady_point == 3){
			str = "��     ��";
			color = ColorList.BLUE;
		}else if(_ClassReady_point == 2){
			str = "��     ��";
			color = ColorList.BLACK;
		}else if(_ClassReady_point == 1){
			str = "��     ��";
			color = ColorList.RED;
		};
		drawString(_g,Size.TXT_B_B,color,str,420,1300);
	}
	
	private void						drawStringClassResult(Graphics _g, String _ClassResult_point ){
		String str 								="�ſ����";
		int	ClassResult_point					=Integer.parseInt(_ClassResult_point);
		Color color 							=ColorList.RED;
		if(ClassResult_point >= 90){
			str = "�ſ���";
			color = ColorList.BLUE;
		}else if(ClassResult_point >= 80){
			str = "��     ��";
			color = ColorList.BLUE;
		}else if(ClassResult_point >= 70){
			str = "��     ��";
			color = ColorList.BLACK;
		}else if(ClassResult_point >= 50){
			str = "��    ��";
			color = ColorList.RED;
		}
		drawString(_g,Size.TXT_B_B,color,str,420,1745);
	}

	private void						drawStringWhite_b_s(Graphics _g, String _str, int _x, int _y){
		_g.setFont(new Font("���� ���",Font.BOLD,Size.TXT_B_S));
		_g.setColor(ColorList.WHITE);
		_g.drawString(_str,_x,_y);
	}
	private void						drawStringBlack_b_m(Graphics _g, String _str, int _x, int _y){
		drawString(_g,Size.TXT_B_M,ColorList.BLACK,_str,_x,_y);
	}
	
	private void						drawStringBlack_b_s(Graphics _g, String _str, int _x, int _y){
		drawString(_g,Size.TXT_B_S,ColorList.BLACK,_str,_x,_y);
	}
	private void						drawStringBlack_m_m(Graphics _g, String _str, int _x, int _y){
		drawString(_g,Size.TXT_M_M,ColorList.BLACK,_str,_x,_y);
	}	
	private void						drawStringNum(Graphics _g, int _num, int _size,Color _color, int _x, int _y){
		String out = "";
		if(_num < 10){
			out = "0"+Integer.toString(_num);
		}else{
			out = Integer.toString(_num);
		}
		drawString(_g,_size,_color,out,_x,_y);
	}

	private void						drawString_s_m(Graphics _g, String _str, Color _color, int _x, int _y){
		drawString(_g,Size.TXT_S_M,_color,_str,_x,_y);
	}
	private void						drawString_m_s(Graphics _g, String _str, Color _color, int _x, int _y){
		drawString(_g,Size.TXT_M_S,_color,_str,_x,_y);
	}

	private void						drawIncorrectCount(Graphics _g, int _incorrectCount, int _x, int _y) throws IOException{

		if(_incorrectCount != 0){
			drawImg(_g,Path.INFO_COUNT_BACKGROUND(), _x, _y-30, 10*13, 3*13);
			drawString(_g,Size.TXT_M_S,ColorList.WHITE,"Wrong",_x+5,_y);
			
			if(_incorrectCount < 10){
				drawString(_g,Size.TXT_M_S,ColorList.GRAY_DARK,Integer.toString(_incorrectCount)+"��",_x+75,_y);
			}else{
				drawString(_g,Size.TXT_M_S,ColorList.GRAY_DARK,Integer.toString(_incorrectCount),_x+75,_y);
			}		
		}
	}
		
	private void						drawPastDate(Graphics _g,long _recentStudyPassDate, int _x, int _y) throws IOException{
		if(_recentStudyPassDate !=  -1l){
			String out ="";
			drawImg(_g,Path.INFO_COUNT_BACKGROUND(), _x, _y-30, 10*13, 3*13);
			drawString(_g,Size.TXT_M_S,ColorList.WHITE,"After",_x+5,_y);
			if(_recentStudyPassDate != 0){
				if(_recentStudyPassDate < 7){
					out = Integer.toString((int) (_recentStudyPassDate) ) + "D";
				}else if(_recentStudyPassDate < 7*2){
					out = "1��";
				}else if(_recentStudyPassDate < 7*3){
					out = "2��";
				}else if(_recentStudyPassDate < 7*4){
					out = "3��";
				}else if(_recentStudyPassDate < 7*5){
					out = "4��";
				}else if(_recentStudyPassDate < 7*6*2){
					out = "1��";
				}else {
					out = "�ʰ�";
				}
				
			}else{
				out = "����";
			}
			drawString(_g,Size.TXT_M_S,ColorList.GRAY_DARK,out,_x+70,_y);
		}
	}
	
	private void						drawStringPage_type(Graphics _g, String _pageType){
		drawString(_g,Size.TXT_M_S,ColorList.BLACK,"< "+_pageType+" >",1476,130);
		drawString(_g,Size.TXT_M_S,ColorList.BLACK,gsdb.ClassName,206,130);
	}
	
	private void						drawPageNum(Graphics _g,  int _pageNum){

		int 						pageNum_point_width 	= 0 ;
		int 						pageNum_point_height 	=  Size.HEIGHT - 75 ;
		
		if(99<_pageNum){
			pageNum_point_width = Size.WIDTH/2 - 45 ;
		}else if(9<_pageNum){
			pageNum_point_width =  Size.WIDTH/2 - 30 ;
		}else{
			pageNum_point_width =  Size.WIDTH/2 - 15 ;
		}
		drawString(_g,Size.TXT_M_B,ColorList.BLACK,Integer.toString(_pageNum),pageNum_point_width,pageNum_point_height);
	}
	
	private void						drawString(Graphics _g, int _txtSize, Color _color, String _str, int _x, int _y){
		_g.setFont(new Font("����",Font.PLAIN,_txtSize));
		_g.setColor(_color);
		_g.drawString(_str,_x,_y);
	}

	private void						drawString_Bold(Graphics _g, int _txtSize, Color _color, String _str, int _x, int _y){
		_g.setFont(new Font("SansSerif",Font.BOLD,_txtSize));
		_g.setColor(_color);
		_g.drawString(_str,_x,_y);
	}
	
	private void						drawButton(Graphics _g, Color _color,String _str, int _x, int _y){
		_g.setColor(_color);
		_g.drawRoundRect(_x, _y - 23, 75, 30, 10, 10);
		drawString_Bold(_g, Size.TXT_S_B, _color, _str, _x+7, _y);
	}
	
	private void						drawImg(Graphics _g, String _path, int _x, int _y, int _width, int _height) throws IOException{
		_g.drawImage(ImageIO.read(new File(_path)), _x, _y, _width, _height, null);
	}
	
	private String 						getStar(int _starCount){
		String out ="";
		for(int i = 0 ; i < _starCount; i++){
			out = out + "��";
		}
		return out;
	}
	
}
