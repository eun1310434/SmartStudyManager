package com.eun1310434.smartstudymanager.make;

import java.util.Comparator;

public class UnitInfo_CORRECT_SORT implements Comparator<UnitInfo_AL> {
    @Override
    public int compare(UnitInfo_AL o1, UnitInfo_AL o2) {
        return o1.correctPercent_double < o2.correctPercent_double ? -1 : o1.correctPercent_double > o2.correctPercent_double ? 1:0;
        
    }
 
}