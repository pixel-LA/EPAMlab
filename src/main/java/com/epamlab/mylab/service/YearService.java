package com.epamlab.mylab.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.epamlab.mylab.entity.YearEntity;
import com.epamlab.mylab.exception.CalendarRelevanceException;

@Service
public class YearService {

    private static final Logger log = LoggerFactory.getLogger(YearService.class);

    public YearEntity yearTypeDefinition(int year) throws CalendarRelevanceException {
        log.info("iiinfo");
        log.error("sdefrgt");
        if (year < 1582) {
            throw new CalendarRelevanceException("Григорианкий календарь был введен в 1582 году.");
        } 

        if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
            YearEntity yearEntity = new YearEntity(year, "Високосный");
            return yearEntity;            
        } else {
            YearEntity yearEntity = new YearEntity(year, "Невисокосный");
            return yearEntity;
        }
    }
}