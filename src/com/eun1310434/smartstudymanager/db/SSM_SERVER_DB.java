package com.eun1310434.smartstudymanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.eun1310434.smartstudymanager.make.QuestionInfo_AL;


public class SSM_SERVER_DB {
    private Connection connection = null;
    private Statement st = null;	


    public SSM_SERVER_DB(){
        try {
        	
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/mysql?useSSL=true&verifyServerCertificate=false","root", "password");
            
            st = connection.createStatement();
            st.executeQuery("USE SSM_SERVER;");
        } catch (SQLException se1) {
            se1.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
    
    public void quit(){
        try {
			st.close();
	        connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                if (st != null)
                    st.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }    
    }
    
    
	public ArrayList<QuestionInfo_AL> Question_DATA_SET(
			String _USER_SN, 
			String _UNIT_SUBJECT, 
			String _UNIT_GRADE, 
			String _UNIT_SEMESTER, 
			String _UNIT_TEST){
		
		
		
        String sql = QUERY.getQuestion(_USER_SN,_UNIT_SUBJECT, _UNIT_GRADE, _UNIT_SEMESTER, _UNIT_TEST); 
    	ArrayList<QuestionInfo_AL> out = new ArrayList<QuestionInfo_AL>();
        ResultSet rs;
		try {
			rs = st.executeQuery(sql);
	        while (rs.next()) {

	        	out.add(new QuestionInfo_AL(
						"17�� 8�� 19�� �����", 						// �򰡳�¥
					 										// _classDate
						rs.getString("QUESTION.question_imgA"), 								// �������� ���
														 	// _questionPath
						rs.getString("QUESTION.question_imgB"), 	// �������� ��� -
															// _solutionPath
						rs.getString("QUESTION.question_workbook"), 	// �������� -
															// _workBook_name
						rs.getString("QUESTION.question_page"), 	// ���������� -
															// _questionPage
						rs.getString("QUESTION.question_number"), 	// ������ȣ -
															// _questionNum
						rs.getString("QUESTION.question_level"), 	// �������� -
															// _questionLevel
						rs.getString("QUESTION.unit_sn"), 	// �����ܿ� -
															// _questionUnit
						rs.getString("QUESTION.question_type"), 	// �������� -
						
						"-", 	// �ֱ��н��� -
															// _recentStudyDate
						"-", 	// �ֱ��н���� -
															// _recentStudyResultCheck
						"-", 	// ����� -
															// _correctPercent
						"-", 	// ����Ƚ�� -
															// _correctCount
						"-"));// ����Ƚ�� -
	        	
	        }
	        rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    	

       int total_count = 0;
       int corrent_count = 0;
       String STUDY_DATE = "";
       String STUDY_RESULT = "";
       
		for(int i = 0 ; i < out.size() ; i++){

			System.out.println(i);
			
			sql = QUERY.getQuestion(_USER_SN, out.get(i).questionSN);
			try {
				rs = st.executeQuery(sql);
	            total_count = 0;
	            corrent_count = 0;
	            STUDY_DATE = "";
	            STUDY_RESULT = "";
		        while (rs.next()) {
		            STUDY_DATE = rs.getString("STUDY.STUDY_DATE");
		            STUDY_RESULT = rs.getString("STUDY.STUDY_RESULT");
		            if(STUDY_RESULT.equals("O")){
		            	corrent_count++;
		            }
		            total_count++;
		        }
		        out.set(
		        		i, 
		        		new QuestionInfo_AL(
		        				out.get(i).classDate, 
		        				out.get(i).question_path, 
		        				out.get(i).solution_path, 
		        				out.get(i).workbook_name, 
		        				out.get(i).questionPage, 
		        				out.get(i).questionNum, 
		        				out.get(i).questionLevel+"", 
		        				out.get(i).questionUnit, 
		        				out.get(i).questionType, 
		        				STUDY_DATE, 
		        				STUDY_RESULT, 
		        				corrent_count*100/total_count+"", 
		        				corrent_count+"", 
		        				(total_count - corrent_count)+""));

		        rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
			
		}
		
		return out;
	}
    
	public void Question_data(String _UNIT_SUBJECT, String _UNIT_GRADE, String _UNIT_SEMESTER, String _UNIT_TEST){
        String sql = QUERY.getQuestion(_UNIT_SUBJECT, _UNIT_GRADE, _UNIT_SEMESTER, _UNIT_TEST); 
    	String	out ="";
    	
        ResultSet rs;
		try {
			rs = st.executeQuery(sql);
	        while (rs.next()) {
	        	
	        	out = 
	        			rs.getString("QUESTION.question_sn")+" - "+
	        			//rs.getString("QUESTION.question_imgA")+" - "+
	        			//rs.getString("QUESTION.question_imgB")+" - "+
	        			rs.getString("QUESTION.question_workbook")+" - "+
	        			rs.getString("QUESTION.question_page")+" - "+
	        			rs.getString("QUESTION.question_number")+" - "+
	        			rs.getString("QUESTION.question_level")+" - "+
	        			rs.getString("QUESTION.unit_sn")+" - "+
	        			rs.getString("QUESTION.question_type");
	        			
	        	
	            System.out.println(out);
	        }
	        rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void Question_data(String _USER_SN, String _QUESTION_SN){

        String sql = QUERY.getQuestion(_USER_SN, _QUESTION_SN); 
        int total_count = 0;
        int corrent_count = 0;
        int continuity_wrong = 0;
        String STUDY_DATE = "";
        String STUDY_RESULT = "";
        
        ResultSet rs;
		try {
			rs = st.executeQuery(sql);
	        while (rs.next()) {
	            STUDY_DATE = rs.getString("STUDY.STUDY_DATE");
	            STUDY_RESULT = rs.getString("STUDY.STUDY_RESULT");
	            if(STUDY_RESULT.equals("O")){
	            	corrent_count++;
	            	continuity_wrong = 0;
	            }else{
	            	continuity_wrong++;
	            }
	            total_count++;
	            
	            System.out.println(STUDY_DATE+"	"+STUDY_RESULT);
	        }

	        System.out.println("�ֱ��н��� : "+STUDY_DATE);
	        System.out.println("����� : "+corrent_count*100/total_count+"%");
	        System.out.println("����Ƚ�� : "+corrent_count+"��");
	        System.out.println("����Ƚ�� : "+(total_count - corrent_count)+"��");
	        System.out.println("��ü�н� : "+total_count+"��");
	        System.out.println("�ֱ��н���� : "+STUDY_RESULT);
	        System.out.println("�ֱٿ��ӿ��� : "+continuity_wrong);
	        rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
