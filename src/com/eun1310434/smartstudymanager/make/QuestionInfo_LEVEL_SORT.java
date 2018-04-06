package com.eun1310434.smartstudymanager.make;

import java.util.Comparator;

public class QuestionInfo_LEVEL_SORT implements Comparator<QuestionInfo_AL> {
    @Override
    public int compare(QuestionInfo_AL o1, QuestionInfo_AL o2) {
        return o1.questionLevel <o2.questionLevel ? -1 : o1.questionLevel > o2.questionLevel ? 1:0;
        
    }
 
}