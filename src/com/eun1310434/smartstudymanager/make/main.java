/*=====================================================================
□ Infomation
  ○ Data : 06.04.2018
  ○ Mail : eun1310434@naver.com
  ○ Web : https://eun1310434.github.io/

□ Function
  ○

□ Study
  ○
  
□ note
  ○ 모든 파일이 저장된 res 폴더는 MakeWorkBook에 저장
  01) Data Check	
		정은택
		String 	StudentName 		=	"정은택";				//이름	-	정은택
		String 	StudentSubject 		=	"영어";				//과목	-	영어
		String 	StudentGrade 		=	"일반";				//학년	-	일반
		String 	StudentSemester 	=	"토익";				//학기	-	토익
		String 	StudentTest 		=	"1차";				//평가	-	1차
		String 	TestDate 			=	"17년 12월 11일 월요일";	//date	-	
		String 	Test_level			=	"R";				//Level	-	A,B,C,D,R	
		int 	Test_count			=	1;					//시험지 갯수
		int		Test_question_count =	20;					//시험지 문제 수

		고도현
		String 	StudentName 		=	"고도현";				//이름
		String 	StudentSubject 		=	"수학";				//과목
		String 	StudentGrade 		=	"중2";				//학년
		String 	StudentSemester 	=	"1학기";				//학기
		String 	StudentTest 		=	"1차";				//평가
		String 	TestDate 			=	"17년 12월 11일 월요일";	//date
		String 	Test_level			=	"R";				//Level	-	A,B,C,D,R	
		int 	Test_count			=	1;					//시험지 갯수
		int		Test_question_count =	20;					//시험지 문제 수
		
		
		
  02) Discard
		mt.Result(); //새로운 학습시 결과를 확인하는것으로 대체
		mt.MyNote();
		mt.Cut("스캔","이채연편집");
		mt.Cut("스캔","고도현편집");
		
		
  03) WorkBook
		MakeWorkBook mw = new MakeWorkBook();
		mw.mkr("MATH_MANY","DB_MATH.xlsx","many");
		mw.mkr("MATH_TWO","DB_MATH.xlsx","two");
		mw.mkr("MATH_맞춤교재_이채연","DB_MATH_맞춤교재.xlsx","many");
		mw.mkr("MATH_맞춤교재_고도현","DB_MATH_맞춤교재.xlsx","many");
		mw.mkr("MATH_문시중_17년_MANY","DB_MATH_문시중_17년.xlsx","many");
		mw.mkr("MATH_문시중_17년_TWO","DB_MATH_문시중_17년.xlsx","two");
		mw.mkr("TOEIC_GRAMMAR_MANY","DB_TOEIC(GRAMMAR).xlsx","many");
		mw.mkr("TOEIC_GRAMMAR_TWO","DB_TOEIC(GRAMMAR).xlsx","two");

  04) MySQL TEST
		DB_Sync d = new DB_Sync();
		d.Drop();
		d.Create();
		d.Reset("고도현","수학","중1","1학기","1차");
		d.Reset("고도현","수학","중1","1학기","2차");
		d.Reset("고도현","수학","중1","2학기","1차");
		d.Reset("고도현","수학","중1","2학기","2차");
		d.Reset("고도현","수학","중2","1학기","1차");
		d.Reset("고도현","수학","중2","1학기","2차");
		d.Reset("고도현","수학","중2","2학기","1차");
		d.Reset("고도현","수학","중2","2학기","2차");
		d.Reset("고도현","수학","중3","1학기","1차");
		d.Reset("고도현","수학","중3","1학기","2차");

		d.Reset("이채연","수학","중1","1학기","1차");
		d.Reset("이채연","수학","중1","1학기","2차");
		d.Reset("이채연","수학","중1","2학기","1차");
		d.Reset("이채연","수학","중1","2학기","2차");
		d.Reset("이채연","수학","중2","1학기","1차");
		d.Reset("이채연","수학","중2","1학기","2차");
		d.Reset("이채연","수학","중2","2학기","1차");
		d.Reset("이채연","수학","중2","2학기","2차");
		d.Reset("이채연","수학","중3","1학기","1차");
		d.Reset("이채연","수학","중3","1학기","2차");

		d.Reset("전현배","수학","중1","1학기","1차");
		d.Reset("전현배","수학","중1","1학기","2차");
		d.Reset("전현배","수학","중1","2학기","1차");
		d.Reset("전현배","수학","중1","2학기","2차");
		d.Reset("전현배","수학","중2","1학기","1차");
		d.Reset("전현배","수학","중2","1학기","2차");
		d.Reset("전현배","수학","중2","2학기","1차");
		d.Reset("전현배","수학","중2","2학기","2차");
		d.Reset("전현배","수학","중3","1학기","1차");
		d.Reset("전현배","수학","중3","1학기","2차");

		d.Reset("최준","수학","중1","1학기","1차");
		d.Reset("최준","수학","중1","1학기","2차");
		d.Reset("최준","수학","중1","2학기","1차");
		d.Reset("최준","수학","중1","2학기","2차");
		d.Reset("최준","수학","중2","1학기","1차");
		d.Reset("최준","수학","중2","1학기","2차");
		d.Reset("최준","수학","중2","2학기","1차");
		d.Reset("최준","수학","중2","2학기","2차");
		d.Reset("최준","수학","중3","1학기","1차");
		d.Reset("최준","수학","중3","1학기","2차");

		d.Reset("박성준","수학","중1","1학기","1차");
		d.Reset("박성준","수학","중1","1학기","2차");
		d.Reset("박성준","수학","중1","2학기","1차");
		d.Reset("박성준","수학","중1","2학기","2차");
		d.Reset("박성준","수학","중2","1학기","1차");
		d.Reset("박성준","수학","중2","1학기","2차");
		d.Reset("박성준","수학","중2","2학기","1차");
		d.Reset("박성준","수학","중2","2학기","2차");
		d.Reset("박성준","수학","중3","1학기","1차");
		d.Reset("박성준","수학","중3","1학기","2차");
=====================================================================*/

package com.eun1310434.smartstudymanager.make;

public class main {
    
	public static void main (String[] args)throws Exception{
		

		String 	StudentName 		=	"정은택";				//name
		String 	StudentSubject 		=	"영어";				//subject
		String 	StudentGrade 		=	"일반";				//section-a
		String 	StudentSemester 	=	"토익";				//section-b
		String 	StudentTest 		=	"1차";				//section-c
		String 	TestDate 			=	"18년 4월 21일 토요일";	//date
		String 	Test_level			=	"A";				//Level : A,B,C,D,R	
		int 	Test_count			=	1;					
		int		Test_question_count =	20;					
		
		MakeTest mt = new MakeTest(StudentName,StudentSubject,StudentGrade,StudentSemester,StudentTest,TestDate);
		mt.Test(Test_level,Test_count,Test_question_count);		
	}
}














