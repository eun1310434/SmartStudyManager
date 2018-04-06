package com.eun1310434.smartstudymanager.tool;

import java.io.File;

public class Path {
	
	private static String PATH_CANONICAL 				= "res"; 
	private static String PATH_WORKBOOK_DB				= PATH_CANONICAL + "\\DataBase";
	private static String PATH_WORKBOOK					= PATH_CANONICAL + "\\WorkBook";
	private static String PATH_BACKGROUND 				= PATH_CANONICAL + "\\Background";
	private static String PATH_FORMULA		 			= PATH_CANONICAL + "\\Formula";
	
	//CANONICAL
	public static String CANONICAL (){
		return PATH_CANONICAL+"\\";
	}

	//QR
	public static void	RESET_QR_FILE(String _path){
		reMakeFolder(_path+"\\QR"); 	
	}
	public static void	DELETE_QR_FILE(String _path){
		DeleteFolder(_path+"\\QR"); 	
	}
	public static String QR_RESOURCE(String _path, int _qrNum){
		return _path+"QR\\"+Integer.toString(_qrNum)+".jpg";
	}
	
	//WORKBOOK
	public static String DB_WORKBOOK (String _db_name){
		return PATH_WORKBOOK_DB+"\\"+_db_name;
	}

	public static String WORKBOOK (String _workbook_name,String _unit_name){
		return PATH_WORKBOOK+"\\"+_workbook_name+"\\"+_unit_name+"\\";
	}

	public static void	RESET_SSM_WORKBOOK_FILE(String _workbook_name,String _unit_name){
		reMakeFolder(PATH_WORKBOOK+"\\"+_workbook_name+"\\"+_unit_name); 
	}
	
	//DB
	public static String DB_STUDENT (String _student_name, String _db_name){
		return PATH_CANONICAL + "\\Students\\"+_student_name+"\\DB\\"+_db_name;
	}
	
	//BackGround Path
	public static String FORMULA_BACKGROUND_IMAGE(){
		return PATH_BACKGROUND +"\\formula_background.jpg";
	}
	
	public static String STUDY_RESULT_QUESTIONLIST_IMAGE(){
		return PATH_BACKGROUND +"\\study_result_questionList.jpg";
	}
	
	public static String SOLUTION_BACKGROUND_IMAGE(){
		return PATH_BACKGROUND +"\\solution_background.jpg";
	}
	
	public static String TEST_BACKGROUND_IMAGE(){
		return PATH_BACKGROUND +"\\test_background.jpg";
	}

	public static String INFO_COUNT_BACKGROUND(){
		return PATH_BACKGROUND +"\\info_count_background.jpg";
	}

	public static String ANSWER_SHEET_BACKGROUND_A(){
		return PATH_BACKGROUND +"\\answer_sheet_background_a.jpg";
	}

	public static String ANSWER_SHEET_BACKGROUND_B(){
		return PATH_BACKGROUND +"\\answer_sheet_background_b.jpg";
	}

	public static String ANSWER_SHEET_MC(){
		return PATH_BACKGROUND +"\\answer_sheet_mc.jpg";
	}
	public static String ANSWER_SHEET_SA(){
		return PATH_BACKGROUND +"\\answer_sheet_sa.jpg";
	}
	
	public static String ANSWER_SHEET_BD(){
		return PATH_BACKGROUND +"\\answer_sheet_bd.jpg";
	}

	public static String QUESTION_EMPTY(){
		return PATH_BACKGROUND +"\\question_empty.jpg";
	}

	public static String WORKBOOK_BACKGROUND(){
		return PATH_BACKGROUND +"\\workbook_background.jpg";
	}
	
	public static String WORKBOOK_BACKGROUND_WIDE(){
		return PATH_BACKGROUND +"\\workbook_background_wide.jpg";
	}
	
	public static String WORKBOOK_BACKGROUND_SHORT_A(){
		return PATH_BACKGROUND +"\\workbook_background_short_a.jpg";
	}
	
	public static String WORKBOOK_BACKGROUND_SHORT_B(){
		return PATH_BACKGROUND +"\\workbook_background_short_b.jpg";
	}

	public static String STUDY_READY_CHECK(){
		return PATH_BACKGROUND +"\\study_ready_check.jpg";
	}

	public static String WORKBOOK_COVER(){
		return PATH_BACKGROUND +"\\workbook_cover.jpg";
	}

	public static String WORKBOOK_STUDY_COVER(){
		return PATH_BACKGROUND +"\\workbook_study_cover.jpg";
	}

	public static String WORKBOOK_TEST_COVER(){
		return PATH_BACKGROUND +"\\workbook_test_cover.jpg";
	}
	
	public static String STUDY_PLAN_SHEET(){
		return PATH_BACKGROUND +"\\study_plan_sheet.jpg";
	}
	
	public static String STUDY_RESULT_SHEET(){
		return PATH_BACKGROUND +"\\study_result_sheet.jpg";
	}
	
	//STUDY
	public static String STUDY(String _student_name, String _className,String _subject){

		return  PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\학습\\";
	}
	public static void	RESET_STUDY_FILE(String _student_name, String _className,String _subject){
		reMakeFolder(PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\학습"); 
	}
	
	//TEST
	public static String TEST(String _student_name, String _className,String _subject){
		return  PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\평가\\";
	}
	public static void	RESET_TEST_FILE(String _student_name, String _className,String _subject){
		reMakeFolder(PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\평가"); 
	}
	
	//RESULT
	public static String RESULT(String _student_name, String _className,String _subject){
		return  PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\결과\\";
	}
	public static void	RESET_RESULT_FILE(String _student_name, String _className,String _subject){
		reMakeFolder(PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\결과"); 
	}
	
	//RECORD
	public static String RECORD(String _student_name, String _className,String _subject){
		return  PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\기록\\";
	}
	public static void	RESET_RECORD_FILE(String _student_name, String _className,String _subject){
		reMakeFolder(PATH_CANONICAL+ "\\Students\\"+_student_name+"\\"+_className + "\\"+_subject+"\\기록"); 
	}
	
	
	//MYNOTE
	public static String MYNOTE(String _student_name){

		return  PATH_CANONICAL + "\\Students\\"+_student_name+"\\MYNOTE\\";
	}
	public static String MYNOTE_RESOURCE (String _student_name, String _myNoteQuestionSN){
		return PATH_CANONICAL + "\\Students\\"+_student_name+"\\MYNOTE\\resource\\"+_myNoteQuestionSN+".jpg";
	}
	public static String MYNOTE(String _student_name, String _subject, String _myNoteFileName){
		return PATH_CANONICAL + "\\Students\\"+_student_name+"\\MYNOTE\\"+_subject+"\\"+_myNoteFileName+"\\";
	}
	public static void	RESET_MYNOTE_FILE(String _student_name, String _Subject, String _MynoteFileName){
		reMakeFolder(PATH_CANONICAL + "\\Students\\"+_student_name+"\\MYNOTE\\"+_Subject+"\\"+_MynoteFileName); 
	}

	//FORMULA
	public static String FORMULA_IMAGE(String _unit){
		return PATH_FORMULA+"\\"+_unit+ ".jpg";
	}
	
	public static void DeleteFolder(String _path){
		//디렉토리 생성 
		File CheckFolder = new File(_path);

	  //해당 디렉토리의 존재여부를 확인
		if(CheckFolder.exists()){
			//있다면 현재 디렉토리 파일을 삭제 
			File[] destroy = CheckFolder.listFiles(); 
			for(File des : destroy){
				des.delete(); 
			}
			CheckFolder.delete();
		}
	}

	public static void reMakeFolder(String _path){
		//디렉토리 생성 
		File CheckFolder = new File(_path);

	  //해당 디렉토리의 존재여부를 확인
		if(!CheckFolder.exists()){
			//없다면 생성
			CheckFolder.mkdirs(); 
		}else{
			//있다면 현재 디렉토리 파일을 삭제 
			File[] destroy = CheckFolder.listFiles(); 
			for(File des : destroy){
			des.delete(); 
			}	
			CheckFolder.mkdirs(); 
		}
	}
	
}
