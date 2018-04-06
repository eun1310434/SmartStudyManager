package com.eun1310434.smartstudymanager.db;

public class QUERY {

	static String query(String _select, String _from, String _where){
        String sql = 	
        		"SELECT"+" "+            
        		_select+" "+
        		
        		"FROM"+" "+
        		_from+" "+
        		
        		"WHERE"+" "+
        		_where+";";

    	return sql;
	}
	
	static String getQuestion(String _USER_SN, String _UNIT_SUBJECT, String _UNIT_GRADE, String _UNIT_SEMESTER, String _UNIT_TEST){
		
		return query(
				"*",
				
				"UNIT, QUESTION, STUDY, USER",
				
				"UNIT.UNIT_SN = QUESTION.UNIT_SN "+
			    "AND "+
			    "QUESTION.QUESTION_SN  = STUDY.QUESTION_SN "+
			    "AND "+
			    "STUDY.USER_SN = USER.USER_SN "+
			    "AND "+
			    "USER.USER_SN = '"+_USER_SN+"' "	+
			    "AND "+
			    "UNIT.UNIT_SUBJECT = '"+_UNIT_SUBJECT+"' "+
			    "AND "+
			    "UNIT.UNIT_GRADE  = '"+_UNIT_GRADE+"' "+
			    "AND "+
			    "UNIT.UNIT_SEMESTER = '"+_UNIT_SEMESTER+"' "+
			    "AND "+
			    "UNIT.UNIT_TEST = '"+_UNIT_TEST+"' "+
			    "GROUP BY "+
			    "QUESTION.QUESTION_SN "+
			    "ORDER BY "+
			    "UNIT.UNIT_SN, QUESTION.QUESTION_PAGE, QUESTION.QUESTION_NUMBER, QUESTION.QUESTION_LEVEL;"
				);
	}
	
	static String getQuestion(String _USER_SN, String _QUESTION_SN){
		
		return query(
				"STUDY.STUDY_DATE, STUDY.STUDY_RESULT",
				
				"UNIT, QUESTION, STUDY, USER",
				
				"UNIT.UNIT_SN = QUESTION.UNIT_SN "+
			    "AND "+
			    "QUESTION.QUESTION_SN  = STUDY.QUESTION_SN "+
			    "AND "+
			    "STUDY.USER_SN = USER.USER_SN "+
			    "AND "+
			    "QUESTION.QUESTION_SN = '"+_QUESTION_SN+"' "+
			    "AND "+
			    "USER.USER_SN = '"+_USER_SN+"'"				
				);
	}

	static String getQuestion(String _UNIT_SUBJECT, String _UNIT_GRADE, String _UNIT_SEMESTER, String _UNIT_TEST){
		
		return query(
				"*",
				
				"UNIT, QUESTION",
				
				"UNIT.UNIT_SN = QUESTION.UNIT_SN "+
			    "AND "+
			    "UNIT.UNIT_SUBJECT = '"+_UNIT_SUBJECT+"' "+
			    "AND "+
			    "UNIT.UNIT_GRADE  = '"+_UNIT_GRADE+"' "+
			    "AND "+
			    "UNIT.UNIT_SEMESTER = '"+_UNIT_SEMESTER+"' "+
			    "AND "+
			    "UNIT.UNIT_TEST = '"+_UNIT_TEST+"' "			
				);
	}
}
