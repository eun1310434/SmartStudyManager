/*=====================================================================
�� Infomation
  �� Data : 06.04.2018
  �� Mail : eun1310434@naver.com
  �� Web : https://eun1310434.github.io/

�� Function
  ��

�� Study
  ��
  
�� note
  �� ��� ������ ����� res ������ MakeWorkBook�� ����
  01) Data Check	
		������
		String 	StudentName 		=	"������";				//�̸�	-	������
		String 	StudentSubject 		=	"����";				//����	-	����
		String 	StudentGrade 		=	"�Ϲ�";				//�г�	-	�Ϲ�
		String 	StudentSemester 	=	"����";				//�б�	-	����
		String 	StudentTest 		=	"1��";				//��	-	1��
		String 	TestDate 			=	"17�� 12�� 11�� ������";	//date	-	
		String 	Test_level			=	"R";				//Level	-	A,B,C,D,R	
		int 	Test_count			=	1;					//������ ����
		int		Test_question_count =	20;					//������ ���� ��

		����
		String 	StudentName 		=	"����";				//�̸�
		String 	StudentSubject 		=	"����";				//����
		String 	StudentGrade 		=	"��2";				//�г�
		String 	StudentSemester 	=	"1�б�";				//�б�
		String 	StudentTest 		=	"1��";				//��
		String 	TestDate 			=	"17�� 12�� 11�� ������";	//date
		String 	Test_level			=	"R";				//Level	-	A,B,C,D,R	
		int 	Test_count			=	1;					//������ ����
		int		Test_question_count =	20;					//������ ���� ��
		
		
		
  02) Discard
		mt.Result(); //���ο� �н��� ����� Ȯ���ϴ°����� ��ü
		mt.MyNote();
		mt.Cut("��ĵ","��ä������");
		mt.Cut("��ĵ","��������");
		
		
  03) WorkBook
		MakeWorkBook mw = new MakeWorkBook();
		mw.mkr("MATH_MANY","DB_MATH.xlsx","many");
		mw.mkr("MATH_TWO","DB_MATH.xlsx","two");
		mw.mkr("MATH_���㱳��_��ä��","DB_MATH_���㱳��.xlsx","many");
		mw.mkr("MATH_���㱳��_����","DB_MATH_���㱳��.xlsx","many");
		mw.mkr("MATH_������_17��_MANY","DB_MATH_������_17��.xlsx","many");
		mw.mkr("MATH_������_17��_TWO","DB_MATH_������_17��.xlsx","two");
		mw.mkr("TOEIC_GRAMMAR_MANY","DB_TOEIC(GRAMMAR).xlsx","many");
		mw.mkr("TOEIC_GRAMMAR_TWO","DB_TOEIC(GRAMMAR).xlsx","two");

  04) MySQL TEST
		DB_Sync d = new DB_Sync();
		d.Drop();
		d.Create();
		d.Reset("����","����","��1","1�б�","1��");
		d.Reset("����","����","��1","1�б�","2��");
		d.Reset("����","����","��1","2�б�","1��");
		d.Reset("����","����","��1","2�б�","2��");
		d.Reset("����","����","��2","1�б�","1��");
		d.Reset("����","����","��2","1�б�","2��");
		d.Reset("����","����","��2","2�б�","1��");
		d.Reset("����","����","��2","2�б�","2��");
		d.Reset("����","����","��3","1�б�","1��");
		d.Reset("����","����","��3","1�б�","2��");

		d.Reset("��ä��","����","��1","1�б�","1��");
		d.Reset("��ä��","����","��1","1�б�","2��");
		d.Reset("��ä��","����","��1","2�б�","1��");
		d.Reset("��ä��","����","��1","2�б�","2��");
		d.Reset("��ä��","����","��2","1�б�","1��");
		d.Reset("��ä��","����","��2","1�б�","2��");
		d.Reset("��ä��","����","��2","2�б�","1��");
		d.Reset("��ä��","����","��2","2�б�","2��");
		d.Reset("��ä��","����","��3","1�б�","1��");
		d.Reset("��ä��","����","��3","1�б�","2��");

		d.Reset("������","����","��1","1�б�","1��");
		d.Reset("������","����","��1","1�б�","2��");
		d.Reset("������","����","��1","2�б�","1��");
		d.Reset("������","����","��1","2�б�","2��");
		d.Reset("������","����","��2","1�б�","1��");
		d.Reset("������","����","��2","1�б�","2��");
		d.Reset("������","����","��2","2�б�","1��");
		d.Reset("������","����","��2","2�б�","2��");
		d.Reset("������","����","��3","1�б�","1��");
		d.Reset("������","����","��3","1�б�","2��");

		d.Reset("����","����","��1","1�б�","1��");
		d.Reset("����","����","��1","1�б�","2��");
		d.Reset("����","����","��1","2�б�","1��");
		d.Reset("����","����","��1","2�б�","2��");
		d.Reset("����","����","��2","1�б�","1��");
		d.Reset("����","����","��2","1�б�","2��");
		d.Reset("����","����","��2","2�б�","1��");
		d.Reset("����","����","��2","2�б�","2��");
		d.Reset("����","����","��3","1�б�","1��");
		d.Reset("����","����","��3","1�б�","2��");

		d.Reset("�ڼ���","����","��1","1�б�","1��");
		d.Reset("�ڼ���","����","��1","1�б�","2��");
		d.Reset("�ڼ���","����","��1","2�б�","1��");
		d.Reset("�ڼ���","����","��1","2�б�","2��");
		d.Reset("�ڼ���","����","��2","1�б�","1��");
		d.Reset("�ڼ���","����","��2","1�б�","2��");
		d.Reset("�ڼ���","����","��2","2�б�","1��");
		d.Reset("�ڼ���","����","��2","2�б�","2��");
		d.Reset("�ڼ���","����","��3","1�б�","1��");
		d.Reset("�ڼ���","����","��3","1�б�","2��");
=====================================================================*/

package com.eun1310434.smartstudymanager.make;

public class main {
    
	public static void main (String[] args)throws Exception{
		

		String 	StudentName 		=	"������";				//name
		String 	StudentSubject 		=	"����";				//subject
		String 	StudentGrade 		=	"�Ϲ�";				//section-a
		String 	StudentSemester 	=	"����";				//section-b
		String 	StudentTest 		=	"1��";				//section-c
		String 	TestDate 			=	"18�� 4�� 21�� �����";	//date
		String 	Test_level			=	"A";				//Level : A,B,C,D,R	
		int 	Test_count			=	1;					
		int		Test_question_count =	20;					
		
		MakeTest mt = new MakeTest(StudentName,StudentSubject,StudentGrade,StudentSemester,StudentTest,TestDate);
		mt.Test(Test_level,Test_count,Test_question_count);		
	}
}














