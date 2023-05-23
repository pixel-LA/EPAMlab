package com.epamlab.mylab.service;

import com.epamlab.mylab.entity.YearEntity;
import com.epamlab.mylab.exception.CalendarRelevanceException;

public class YearEntityLogic {

    public static String yearTypeDefinition(int year) {
            
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            return "Leap year";            
        } else {
            return "Not a leap year";
        }
    }

    public static Integer parseData(String value) throws CalendarRelevanceException, NumberFormatException {
        Integer year = Integer.parseInt(value);
        if (year < 1582) throw new CalendarRelevanceException("The Gregorian calendar was introduced in 1582.");
        return year;
    }

    public static Boolean validateData(String value) {
        Integer year = 0;
        try {
            year = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return false;
        }
        return !(year < 0);
    }

    public static void setAll(YearEntity yearEntity, Integer value) {
        yearEntity.setYear(value);
        yearEntity.setTypeOfYear(YearEntityLogic.yearTypeDefinition(value));
        yearEntity.setId((long) value.hashCode());
    }
}
