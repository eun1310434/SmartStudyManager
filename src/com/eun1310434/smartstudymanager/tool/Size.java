package com.eun1310434.smartstudymanager.tool;

public class Size {

	/*�۾�*/
	public static int 						TXT_B_B_B 						= 800 ; // [����] �۾� ������ - b_b_b
	public static int 						TXT_B_B_M 						= 300 ; // [����] �۾� ������ - b_b
	public static int 						TXT_B_B 						= 150 ; // [����] �۾� ������ - b_b
	public static int 						TXT_B_M 						= 140 ; // [����] �۾� ������ - b_m
	public static int 						TXT_B_S 						= 60 ;  // [����] �۾� ������ - b_s,  -> ������ȣ
	public static int 						TXT_QUESTION_NUM 				= 80 ;  // [����] �۾� ������ - b_s,  -> ������ȣ
	public static int 						TXT_ANSWERSHEET_NUM 			= 60 ;  // [����] �۾� ������ - b_s,  -> ������ȣ
	
	public static int 						TXT_M_B 						= 50 ;  // [����] �۾� ������ - m_b
	public static int 						TXT_M_M 						= 40 ;  // [����] �۾� ������ - m_m
	public static int 						TXT_M_S 						= 30 ;  // [����] �۾� ������ - m_s
	
	public static int 						TXT_S_B 						= 20 ;  // [����] �۾� ������ - m_b
	public static int 						TXT_S_M 						= 14 ;  // [����] �۾� ������ - m_m
	public static int 						TXT_S_S 						= 11 ;  	// [����] �۾� ������ - m_s

	/*����*/
	public static int 						WIDTH 							= 2100; // [����] A4���� ������ - ����
	public static int 						HEIGHT		 					= 2970; // [����] A4���� ������ - ����
	public static int 						WIDTH_GAP 						= 220; 	// [����] A4���� ������ - ����
	public static int 						HEIGHT_GAP 						= 170; 	// [����] A4���� ������ - ����
	
	/*�н���*/	
	public static int 						STUDY_FOMULA_GAP_TOP	 		= 1919; 			// [����] A4���� ������ - ����
	public static int 						STUDY_QUESTION_GAP_TOP 			= 260; 				// [����] A4���� ������ - ����
	public static int 						STUDY_QUESTION_WIDE_GAP_BOTTOM 	= HEIGHT - 2709; 	// [����] A4���� ������ - ����

	/*����*/
	public static int 						QUESTION_GAP_BOTTOM_DOWN	 	= 1885;
	public static int 						QUESTION_BOTTOM_HEIGHT	 		= 910; 
	public static int 						QUESTION_TOP_GAP	 			= 260; 
	
	public static int 						QUESTION_TWO_HEIGHT		 		= 2533 - QUESTION_TOP_GAP; //���� 
	
	public static int 						QUESTION_MANY_START_POINT_WIDTH_LEFT 		= 220;//����(����)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						QUESTION_MANY_START_POINT_WIDTH_RIGHT 		= 1064;//����(������)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						QUESTION_MANY_START_POINT_HEIGHT	 		= 165; //������ �Է��ϴ� ó�� ��ġ (H)
	public static int 						QUESTION_MANY_QUSTION_BETWEEN_GAP_TOP	 	= 400; //������ ���� ������ ���� ����
	public static int 						QUESTION_MANY_INFO_HEIGHT	 				= 80; //������ ������ �Է��ϴ� ���� ����
	public static int 						QUESTION_MANY_GAP_BOTTOM	 				= HEIGHT - 2818; //������ ���� ������ ���� ����
	public static int 						QUESTION_MANY_QUESTION_HEIGHT	 			= HEIGHT - QUESTION_MANY_GAP_BOTTOM - QUESTION_MANY_START_POINT_HEIGHT - QUESTION_MANY_INFO_HEIGHT; //������ ������ �Է��ϴ� ���� ����
	
	/*������*/
	public static int 						FORMULA_GAP_TOP 				= 345; 	
	public static int 						FORMULA_HEIGHT	 				= 2819 - FORMULA_GAP_TOP; 
	public static int 						FORMULA_UNIT_NAME_WIDTH 		= 235; 	
	public static int 						FORMULA_UNIT_NAME_HEIGHT 		= 297; 	
	
	/*�ؼ���*/
	public static int 						SOLUTION_MANY_START_POINT_WIDTH_LEFT 		= 220;//�ؼ�(����)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						SOLUTION_MANY_START_POINT_WIDTH_RIGHT 		= 1064;//�ؼ�(������)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						SOLUTION_MANY_START_POINT_HEIGHT	 		= 280; //�ؼ��� �Է��ϴ� ó�� ��ġ (H)
	public static int 						SOLUTION_MANY_SOLUTION_BETWEEN_GAP_TOP	 	= 50; //�ؼ��� �ؼ� ������ ���� ����
	public static int 						SOLUTION_MANY_INFO_HEIGHT	 				= 80; //�ؼ��� ������ �Է��ϴ� ���� ����
	public static int 						SOLUTION_MANY_GAP_BOTTOM	 				= HEIGHT - 2818; //�ؼ��� �Ʒ� ����
	public static int 						SOLUTION_MANY_SOLUTION_HEIGHT	 			= HEIGHT-SOLUTION_MANY_GAP_BOTTOM - SOLUTION_MANY_START_POINT_HEIGHT - SOLUTION_MANY_INFO_HEIGHT; //������ ������ �Է��ϴ� ���� ����
	
	public static int 						QR_TOP_GAP	 			= 0; // 2538
	public static int 						QR_LEFT_GAP	 			= 720; //842
	public static int 						QR_HEIGHT	 			= 90;
	public static int 						QR_WIDTH	 			= 90;

	public static int 						ANSWERSHEET_START_POINT_WIDTH_LEFT 			= 107;//����(����)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						ANSWERSHEET_START_POINT_WIDTH_RIGHT 		= 1052;//����(������)�� �Է��ϴ� ó�� ��ġ (W)
	public static int 						ANSWERSHEET_START_POINT_HEIGHT_FIRST	 	= 618; //������ �Է��ϴ� ó�� ��ġ (H)
	public static int 						ANSWERSHEET_START_POINT_HEIGHT_SECOND	 	= 190; //������ �Է��ϴ� ó�� ��ġ (H)
	public static int 						ANSWERSHEET_GAP_BOTTOM	 					= 2824; //�ؼ��� �Ʒ� ����
	
}
