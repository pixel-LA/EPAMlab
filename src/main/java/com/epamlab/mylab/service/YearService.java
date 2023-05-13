package com.epamlab.mylab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epamlab.mylab.entity.YearEntity;

@Service
public class YearService {

    private static final Logger log = LoggerFactory.getLogger(YearService.class);

    public YearEntity yearTypeDefinition(int year) {
        log.info("Checking the year");
            
        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            YearEntity yearEntity = new YearEntity(year, "Leap year");
            return yearEntity;            
        } else {
            YearEntity yearEntity = new YearEntity(year, "Not a leap year");
            return yearEntity;
        }
    }
}