package com.epamlab.mylab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epamlab.mylab.exception.CalendarRelevanceException;
import com.epamlab.mylab.service.YearService;



@RestController
@RequestMapping
public class YearController {
    private static final Logger log = LoggerFactory.getLogger(YearController.class);

    private YearService yearService;

    @GetMapping("/year")
    public ResponseEntity<?> getTypeOfYear(@RequestParam(name = "year", required = false, defaultValue = "") String year) {
        
        try {
            Integer yearNum = Integer.parseInt(year);
            if (yearNum < 1582) {
                throw new CalendarRelevanceException("The Gregorian calendar was introduced in 1582.");
            }  
            return ResponseEntity.ok().body(yearService.yearTypeDefinition(yearNum));
        }   catch (CalendarRelevanceException e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());          
        }   catch (NumberFormatException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Check the correctness of the entered data.");
        }
    }
}