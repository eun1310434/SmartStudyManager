package com.eun1310434.smartstudymanager.tool;

public class Size {

	/*글씨*/
	public static int 						TXT_B_B_B 						= 800 ; // [선언] 글씨 사이즈 - b_b_b
	public static int 						TXT_B_B_M 						= 300 ; // [선언] 글씨 사이즈 - b_b
	public static int 						TXT_B_B 						= 150 ; // [선언] 글씨 사이즈 - b_b
	public static int 						TXT_B_M 						= 140 ; // [선언] 글씨 사이즈 - b_m
	public static int 						TXT_B_S 						= 60 ;  // [선언] 글씨 사이즈 - b_s,  -> 문제번호
	public static int 						TXT_QUESTION_NUM 				= 80 ;  // [선언] 글씨 사이즈 - b_s,  -> 문제번호
	public static int 						TXT_ANSWERSHEET_NUM 			= 60 ;  // [선언] 글씨 사이즈 - b_s,  -> 문제번호
	
	public static int 						TXT_M_B 						= 50 ;  // [선언] 글씨 사이즈 - m_b
	public static int 						TXT_M_M 						= 40 ;  // [선언] 글씨 사이즈 - m_m
	public static int 						TXT_M_S 						= 30 ;  // [선언] 글씨 사이즈 - m_s
	
	public static int 						TXT_S_B 						= 20 ;  // [선언] 글씨 사이즈 - m_b
	public static int 						TXT_S_M 						= 14 ;  // [선언] 글씨 사이즈 - m_m
	public static int 						TXT_S_S 						= 11 ;  	// [선언] 글씨 사이즈 - m_s

	/*용지*/
	public static int 						WIDTH 							= 2100; // [선언] A4용지 사이즈 - 가로
	public static int 						HEIGHT		 					= 2970; // [선언] A4용지 사이즈 - 세로
	public static int 						WIDTH_GAP 						= 220; 	// [선언] A4용지 사이즈 - 가로
	public static int 						HEIGHT_GAP 						= 170; 	// [선언] A4용지 사이즈 - 가로
	
	/*학습지*/	
	public static int 						STUDY_FOMULA_GAP_TOP	 		= 1919; 			// [선언] A4용지 사이즈 - 가로
	public static int 						STUDY_QUESTION_GAP_TOP 			= 260; 				// [선언] A4용지 사이즈 - 가로
	public static int 						STUDY_QUESTION_WIDE_GAP_BOTTOM 	= HEIGHT - 2709; 	// [선언] A4용지 사이즈 - 가로

	/*문제*/
	public static int 						QUESTION_GAP_BOTTOM_DOWN	 	= 1885;
	public static int 						QUESTION_BOTTOM_HEIGHT	 		= 910; 
	public static int 						QUESTION_TOP_GAP	 			= 260; 
	
	public static int 						QUESTION_TWO_HEIGHT		 		= 2533 - QUESTION_TOP_GAP; //문제 
	
	public static int 						QUESTION_MANY_START_POINT_WIDTH_LEFT 		= 220;//문제(왼쪽)를 입력하는 처음 위치 (W)
	public static int 						QUESTION_MANY_START_POINT_WIDTH_RIGHT 		= 1064;//문제(오른쪽)를 입력하는 처음 위치 (W)
	public static int 						QUESTION_MANY_START_POINT_HEIGHT	 		= 165; //문제를 입력하는 처음 위치 (H)
	public static int 						QUESTION_MANY_QUSTION_BETWEEN_GAP_TOP	 	= 400; //문제와 문제 사이의 높이 간격
	public static int 						QUESTION_MANY_INFO_HEIGHT	 				= 80; //문제의 정보를 입력하는 높이 간격
	public static int 						QUESTION_MANY_GAP_BOTTOM	 				= HEIGHT - 2818; //문제와 문제 사이의 높이 간격
	public static int 						QUESTION_MANY_QUESTION_HEIGHT	 			= HEIGHT - QUESTION_MANY_GAP_BOTTOM - QUESTION_MANY_START_POINT_HEIGHT - QUESTION_MANY_INFO_HEIGHT; //문제의 정보를 입력하는 높이 간격
	
	/*공식집*/
	public static int 						FORMULA_GAP_TOP 				= 345; 	
	public static int 						FORMULA_HEIGHT	 				= 2819 - FORMULA_GAP_TOP; 
	public static int 						FORMULA_UNIT_NAME_WIDTH 		= 235; 	
	public static int 						FORMULA_UNIT_NAME_HEIGHT 		= 297; 	
	
	/*해설지*/
	public static int 						SOLUTION_MANY_START_POINT_WIDTH_LEFT 		= 220;//해설(왼쪽)를 입력하는 처음 위치 (W)
	public static int 						SOLUTION_MANY_START_POINT_WIDTH_RIGHT 		= 1064;//해설(오른쪽)를 입력하는 처음 위치 (W)
	public static int 						SOLUTION_MANY_START_POINT_HEIGHT	 		= 280; //해설를 입력하는 처음 위치 (H)
	public static int 						SOLUTION_MANY_SOLUTION_BETWEEN_GAP_TOP	 	= 50; //해설와 해설 사이의 높이 간격
	public static int 						SOLUTION_MANY_INFO_HEIGHT	 				= 80; //해설의 정보를 입력하는 높이 간격
	public static int 						SOLUTION_MANY_GAP_BOTTOM	 				= HEIGHT - 2818; //해설지 아래 간격
	public static int 						SOLUTION_MANY_SOLUTION_HEIGHT	 			= HEIGHT-SOLUTION_MANY_GAP_BOTTOM - SOLUTION_MANY_START_POINT_HEIGHT - SOLUTION_MANY_INFO_HEIGHT; //문제의 정보를 입력하는 높이 간격
	
	public static int 						QR_TOP_GAP	 			= 0; // 2538
	public static int 						QR_LEFT_GAP	 			= 720; //842
	public static int 						QR_HEIGHT	 			= 90;
	public static int 						QR_WIDTH	 			= 90;

	public static int 						ANSWERSHEET_START_POINT_WIDTH_LEFT 			= 107;//답지(왼쪽)를 입력하는 처음 위치 (W)
	public static int 						ANSWERSHEET_START_POINT_WIDTH_RIGHT 		= 1052;//답지(오른쪽)를 입력하는 처음 위치 (W)
	public static int 						ANSWERSHEET_START_POINT_HEIGHT_FIRST	 	= 618; //문제를 입력하는 처음 위치 (H)
	public static int 						ANSWERSHEET_START_POINT_HEIGHT_SECOND	 	= 190; //문제를 입력하는 처음 위치 (H)
	public static int 						ANSWERSHEET_GAP_BOTTOM	 					= 2824; //해설지 아래 간격
	
}
