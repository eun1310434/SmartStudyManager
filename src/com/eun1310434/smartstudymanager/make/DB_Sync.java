package com.eun1310434.smartstudymanager.make;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.eun1310434.smartstudymanager.tool.Path;

public class DB_Sync {

	
    private Connection 			con 	= null;
    private String 				url 	= null;
    private String 				user 	= null;
    private String 				passwd 	= null;
	private Statement			stmt 	= null;
	private GetStudentData 		gsdb 	= null; 
	
	public DB_Sync(
			) {
        url 	= "jdbc:mysql://localhost/mysql?useSSL=true&verifyServerCertificate=false";
        user 	= "root";
        passwd 	= "password";
	}
	
	public void Drop(){
        connect();
        SSM_SERVER_USE();
        SSM_SERVER_DROP();
        close();
	}

    private void SSM_SERVER_DROP(){
    	String DROP[] = {"DROP TABLE STUDY;","DROP TABLE TEST;","DROP TABLE QUESTION;","DROP TABLE UNIT;"};
    	
    	for(int i = 0 ; i < DROP.length ; i++){
            try {
    			stmt = con.createStatement();
    			stmt.executeUpdate(DROP[i]);
    			System.out.println(DROP[i]);
            } catch (SQLException ex) {
        		System.err.println("SQLException : " + ex.getMessage());
    			System.err.println("SQLException : " + DROP[i]);
            } finally {
            	try{
            		if( stmt != null) stmt.close();
            	}catch(Exception e){}
        	}
    	}
    }

	
	public void Create(){
        connect();
        SSM_SERVER_USE();
        SSM_SERVER_CREATE();
        close();
	}


    private void SSM_SERVER_CREATE(){ // 사용자 정보는 생략
    	String CREATE[] = 
    		{
    				"CREATE TABLE IF NOT EXISTS `SSM_SERVER`.`UNIT` (`unit_sn` VARCHAR(45) NOT NULL, `unit_subject` VARCHAR(45) NOT NULL, `unit_grade` VARCHAR(45) NOT NULL, `unit_num` VARCHAR(45) NOT NULL, `unit_step` VARCHAR(45) NOT NULL, `unit_name` VARCHAR(100) NOT NULL, PRIMARY KEY (`unit_sn`)) ENGINE = InnoDB;",
    				"CREATE TABLE IF NOT EXISTS `SSM_SERVER`.`QUESTION` ( `question_sn` VARCHAR(100) NOT NULL, `question_workbook` VARCHAR(100) NOT NULL, `question_page` INT NOT NULL, `question_number` INT NOT NULL, `question_level` INT NOT NULL, `unit_sn` VARCHAR(45) NOT NULL, `question_type` VARCHAR(45) NOT NULL, PRIMARY KEY (`question_sn`), INDEX `fk_QUESTION_UNIT1_idx` (`unit_sn` ASC), CONSTRAINT `fk_QUESTION_UNIT1` FOREIGN KEY (`unit_sn`) REFERENCES `SSM_SERVER`.`UNIT` (`unit_sn`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB;",
    				"CREATE TABLE IF NOT EXISTS `SSM_SERVER`.`STUDY` ( `user_sn` VARCHAR(100) NOT NULL, `question_sn` VARCHAR(100) NOT NULL, `study_date` DATE NOT NULL, `study_result` VARCHAR(45) NOT NULL, INDEX `fk_STUDY_QUESTION1_idx` (`question_sn` ASC), INDEX `fk_STUDY_USER1_idx` (`user_sn` ASC), CONSTRAINT `fk_STUDY_QUESTION1` FOREIGN KEY (`question_sn`) REFERENCES `SSM_SERVER`.`QUESTION` (`question_sn`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_STUDY_USER1` FOREIGN KEY (`user_sn`) REFERENCES `SSM_SERVER`.`USER` (`user_sn`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB;",
    				"CREATE TABLE IF NOT EXISTS `SSM_SERVER`.`TEST` ( `user_sn` VARCHAR(100) NOT NULL, `unit_sn` VARCHAR(45) NOT NULL, `test_grade` VARCHAR(45) NOT NULL, `test_semester` VARCHAR(45) NOT NULL, `test_term` VARCHAR(45) NOT NULL, INDEX `fk_TEST_USER1_idx` (`user_sn` ASC), INDEX `fk_TEST_UNIT1_idx` (`unit_sn` ASC), PRIMARY KEY (`user_sn`, `unit_sn`), CONSTRAINT `fk_TEST_USER1` FOREIGN KEY (`user_sn`) REFERENCES `SSM_SERVER`.`USER` (`user_sn`) ON DELETE NO ACTION ON UPDATE NO ACTION, CONSTRAINT `fk_TEST_UNIT1` FOREIGN KEY (`unit_sn`) REFERENCES `SSM_SERVER`.`UNIT` (`unit_sn`) ON DELETE NO ACTION ON UPDATE NO ACTION) ENGINE = InnoDB;"
    		};
    	
    	for(int i = 0 ; i < CREATE.length ; i++){
            try {
    			stmt = con.createStatement();
    			stmt.executeUpdate(CREATE[i]);
    			System.out.println(CREATE[i]);
            } catch (SQLException ex) {
        		System.err.println("SQLException : " + ex.getMessage());
    			System.err.println("SQLException : " + CREATE[i]);
            } finally {
            	try{
            		if( stmt != null) stmt.close();
            	}catch(Exception e){}
        	}
    	}
    }

	
	public void Reset(
			String _StudentName,
			String _StudentSubject,
			String _StudentGrade,
			String _StudentSemester,
			String _StudentTest)throws Exception{
		
		String db_name	=  	_StudentName+"_"+_StudentSubject+"_"+_StudentGrade+"_"+_StudentSemester+"_"+_StudentTest+".xlsx";
		
		gsdb = new GetStudentData(
				Path.DB_STUDENT(_StudentName,db_name),
				_StudentName,
				_StudentSubject,
				_StudentGrade,
				_StudentSemester,
				_StudentTest,
				"00-00-00"
				);
        connect();
    	SSM_SERVER_USE();
    	
    	SSM_SERVER_USER_INSERT();
    	
    	SSM_SERVER_DELETE();
    	SSM_SERVER_UNIT_INSERT();
    	SSM_SERVER_TEST_INSERT();
    	SSM_SERVER_QUESTION_INSERT();
    	SSM_SERVER_STUDY_INSERT();
        close();
	}
	
	
	

    public void connect(){
    	try{
            Class.forName("com.mysql.jdbc.Driver");
        	con = DriverManager.getConnection(url,user, passwd);
        	
    	}catch(java.lang.ClassNotFoundException e1){
    		System.err.println("ClassNotFoundException : ");
    		System.err.println("드라이버 로딩 오류 : "+e1.getMessage());
    	}catch (SQLException e2) {
    		System.err.println("데이터베이스 접속 오류 : " + e2.getMessage());
        }
	}
    
    private void SSM_SERVER_USE(){
        try {
            stmt = con.createStatement();
            stmt.executeQuery("USE SSM_SERVER;");
        } catch (SQLException ex) {
    		System.err.println("SQLException : " + ex.getMessage());
        } finally {
        	try{
        		if( stmt != null) stmt.close();
        	}catch(Exception e){}
    	}
    }
    private void SSM_SERVER_USER_INSERT(){
    	String insert = "INSERT INTO SSM_SERVER.USER VALUE('"+gsdb.StudentName+"','"+gsdb.StudentName+"','010-000-000','-','-','student','"+gsdb.StudentName+"','"+gsdb.StudentName+"');";
    	//INSERT INTO SSM_SERVER.USER VALUE('고도현','abcd1234','010-2108-4102','-','1.1.2','student','고도현','고도현');
        try {
			stmt = con.createStatement();
			stmt.executeUpdate(insert);
			System.out.println(insert);
        } catch (SQLException ex) {
    		System.err.println("SQLException : " + ex.getMessage());
			System.err.println("SQLException : " + insert);
        } finally {
        	try{
        		if( stmt != null) stmt.close();
        	}catch(Exception e){}
    	}
    }


    private void SSM_SERVER_DELETE(){
    	String delete = 
    			"DELETE study, test  "+
    			"FROM "+
    			"user, study, question, unit, test "+
    			"WHERE "+
    			"user.user_sn = study.user_sn "+
    			"AND "+
    			"question.question_sn = study.question_sn "+
    			"AND "+
    			"unit.unit_sn = question.unit_sn "+
    			"AND "+
    			"test.user_sn = user.user_sn "+
    			"AND "+
    			"unit.unit_sn = test.unit_sn "+
    			"AND "+
    			"user.user_sn = '"+gsdb.StudentName+"' "+
    			"AND "+
    			"unit.unit_subject = '"+gsdb.StudentSubject+"'"+
    			"AND "+
    			"test.test_grade = '"+gsdb.StudentGrade+"'"+
    			"AND "+
    			"test.test_semester = '"+gsdb.StudentSemester+"'"+
    			"AND "+
    			"test.test_term = '"+gsdb.StudentTest+"';";
    	
        try {
			stmt = con.createStatement();
			stmt.executeUpdate(delete);
			System.out.println(delete);
        } catch (SQLException ex) {
    		System.err.println("SQLException : " + ex.getMessage());
			System.err.println("SQLException : " + delete);
        } finally {
        	try{
        		if( stmt != null) stmt.close();
        	}catch(Exception e){}
    	}
    }

    
    private void SSM_SERVER_UNIT_INSERT(){
		for (int i = 0 ; i < gsdb.getUnitDataList_UNIT_SORT().size() ; i++){
	        try {
				stmt = con.createStatement();
				stmt.executeUpdate(gsdb.getUnitDataList_UNIT_SORT().get(i).QueryA);
				System.out.println(gsdb.getUnitDataList_UNIT_SORT().get(i).QueryA);
	        } catch (SQLException ex) {
	    		System.err.println("SQLException : " + ex.getMessage());
				System.err.println("SQLException : " + gsdb.getUnitDataList_UNIT_SORT().get(i).QueryA);
	        } finally {
	        	try{
	        		if( stmt != null) stmt.close();
	        	}catch(Exception e){}
	    	}
		}
    }
    
    private void SSM_SERVER_TEST_INSERT(){
		for (int i = 0 ; i < gsdb.getUnitDataList_UNIT_SORT().size() ; i++){
	        try {
				stmt = con.createStatement();
				stmt.executeUpdate(gsdb.getUnitDataList_UNIT_SORT().get(i).QueryB);
				System.out.println(gsdb.getUnitDataList_UNIT_SORT().get(i).QueryB);
	        } catch (SQLException ex) {
	    		System.err.println("SQLException : " + ex.getMessage());
				System.err.println("SQLException : " + gsdb.getUnitDataList_UNIT_SORT().get(i).QueryB);
	        } finally {
	        	try{
	        		if( stmt != null) stmt.close();
	        	}catch(Exception e){}
	    	}
		}
    }
    private void SSM_SERVER_QUESTION_INSERT(){
		for (int i = 0 ; i < gsdb.getTotalQuestionDataList().size() ; i++){
	        try {
				stmt = con.createStatement();
				stmt.executeUpdate(gsdb.getTotalQuestionDataList().get(i).Query);
				System.out.println(gsdb.getTotalQuestionDataList().get(i).Query);
	        } catch (SQLException ex) {
	    		System.err.println("SQLException : " + ex.getMessage());
				System.err.println("SQLException : " + gsdb.getTotalQuestionDataList().get(i).Query);
	        } finally {
	        	try{
	        		if( stmt != null) stmt.close();
	        	}catch(Exception e){}
	    	}
		}
    }

    private void SSM_SERVER_STUDY_INSERT(){
		for (int i = 0 ; i < gsdb.StudyDataList.size() ; i++){
	        try {
				stmt = con.createStatement();
				stmt.executeUpdate(gsdb.StudyDataList.get(i).Query);
				System.out.println(gsdb.StudyDataList.get(i).Query);
	        } catch (SQLException ex) {
	    		System.err.println("SQLException : " + ex.getMessage());
	    		System.err.println("SQLException : " + gsdb.StudyDataList.get(i).Query);
	        } finally {
	        	try{
	        		if( stmt != null) stmt.close();
	        	}catch(Exception e){}
	    	}
		}
    }
    
    
    public void close(){
        try {
            if (con != null) con.close();
        } catch (Exception e) {}
    }
}
