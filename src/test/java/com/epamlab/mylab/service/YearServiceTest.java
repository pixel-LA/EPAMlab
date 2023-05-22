// package com.epamlab.mylab.service;

// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// public class YearServiceTest {
//     public final String notALeapYear = "Not a leap year";
//     public final String leapYear = "Leap year";
//     @Test
//     public void shouldReturnTypeOfYear() {
//         YearService yearService = new YearService();    
//         Assertions.assertNotNull(yearService.yearTypeDefinition(1582));
//         Assertions.assertEquals(notALeapYear, yearService.yearTypeDefinition(1900).getTypeOfYear());
//         Assertions.assertEquals(leapYear, yearService.yearTypeDefinition(2000).getTypeOfYear());
//         Assertions.assertEquals(notALeapYear ,yearService.yearTypeDefinition(2001).getTypeOfYear());
//         Assertions.assertEquals(leapYear, yearService.yearTypeDefinition(2004).getTypeOfYear());
//     }

// }
