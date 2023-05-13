package com.epamlab.mylab.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.epamlab.mylab.entity.YearEntity;

public class YearServiceTest {

    @Test
    public void shouldReturnTypeOfYear() {
        YearService yearService = new YearService();    
        List<YearEntity> years = getYearEntity();
        Assertions.assertNotNull(yearService.yearTypeDefinition(1582));
        Assertions.assertEquals(yearService.yearTypeDefinition(1900).getTypeOfYear(), years.get(3).getTypeOfYear());
        Assertions.assertEquals(yearService.yearTypeDefinition(2000).getTypeOfYear(), years.get(2).getTypeOfYear());
        Assertions.assertEquals(yearService.yearTypeDefinition(2001).getTypeOfYear(), years.get(1).getTypeOfYear());
        Assertions.assertEquals(yearService.yearTypeDefinition(2004).getTypeOfYear(), years.get(0).getTypeOfYear());
    }


    private List<YearEntity> getYearEntity() {
        YearEntity year2004 = new YearEntity();
        YearEntity year2001 = new YearEntity();
        YearEntity year2000 = new YearEntity();
        YearEntity year1900 = new YearEntity();
        
        year2004.setYear(2004);
        year2004.setTypeOfYear("Leap year");

        year2001.setYear(2004);
        year2001.setTypeOfYear("Not a leap year");

        year2000.setYear(2004);
        year2000.setTypeOfYear("Leap year");

        year1900.setYear(2004);
        year1900.setTypeOfYear("Not a leap year");

        return List.of(year2004, year2001, year2000, year1900);
    }
}
