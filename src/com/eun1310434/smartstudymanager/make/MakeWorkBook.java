package com.eun1310434.smartstudymanager.make;

import java.util.ArrayList;

import com.eun1310434.smartstudymanager.tool.Path;


public class MakeWorkBook {
	
	private MakePage    		PAGE				= new MakePage();
	
	public void mkr(String _name, String _db, String _type) throws Exception{
		
		String 				questionName 		= _name+"_QUESTION";
		String 				solutionName 		= _name+"_SOLUTION";
		GetWorkBookData 	gwbd 		 		= new GetWorkBookData(Path.DB_WORKBOOK(_db));	
		
		String 				unit 				= gwbd.QuestionDataList.get(0).questionUnit;
		String 				unitFileName 		= unit.substring(0,unit.lastIndexOf("-"));
		
		Path.RESET_SSM_WORKBOOK_FILE(questionName,unitFileName);
		Path.RESET_SSM_WORKBOOK_FILE(solutionName,unitFileName);
		
		String 				path_question		= Path.WORKBOOK(questionName,unitFileName);
		String 				path_solution		= Path.WORKBOOK(solutionName,unitFileName);
		
		ArrayList<QuestionInfo_AL> UnitQuestionDataList  = new ArrayList<QuestionInfo_AL>();		
		for(int i = 0 ; i < gwbd.QuestionDataList.size() ; i++){
			if(unit.equals(gwbd.QuestionDataList.get(i).questionUnit)){
				UnitQuestionDataList.add(gwbd.QuestionDataList.get(i));
			}else{			
				System.out.println("MakeWorkBook - "+unit);
				PAGE.makeFormulaPage_One		(unit+"_A유형"	,path_question		,unit);
				PAGE.makeQuestionPage			(unit+"_B학습지"	,path_question		,UnitQuestionDataList,	"odd",	_type);
				PAGE.makeSolutionPage_Many		(unit+"_C해답지"	,path_solution		,UnitQuestionDataList,	"even");
				
				UnitQuestionDataList.clear();
				
				unit = gwbd.QuestionDataList.get(i).questionUnit;
				i = i - 1;
				if(unitFileName.equals(unit.substring(0,unit.lastIndexOf("-")))){
					
				}else{
					
					unitFileName 	= unit.substring(0,unit.lastIndexOf("-"));
					
					Path.RESET_SSM_WORKBOOK_FILE(questionName,unitFileName);
					Path.RESET_SSM_WORKBOOK_FILE(solutionName,unitFileName);
					
					path_question		= Path.WORKBOOK(questionName,unitFileName);
					path_solution		= Path.WORKBOOK(solutionName,unitFileName);
				}
			}
		}

		System.out.println("MakeWorkBook - "+unit);
		PAGE.makeFormulaPage_One		(unit+"_A유형"	,path_question		,unit);
		PAGE.makeQuestionPage			(unit+"_B학습지"	,path_question		,UnitQuestionDataList,	"odd",	_type);
		PAGE.makeSolutionPage_Many		(unit+"_C해답지"	,path_solution		,UnitQuestionDataList,	"even");
		
	}
}
