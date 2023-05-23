package com.epamlab.mylab.controllers;

import com.epamlab.mylab.exception.CalendarRelevanceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);
    
    @ExceptionHandler(value = CalendarRelevanceException.class)
    public ResponseStatusException negativeException() {
        log.error("Error: The Gregorian calendar was introduced in 1582");
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Gregorian calendar was introduced in 1582.");
    }

    @ExceptionHandler(value = NumberFormatException.class)
    public ResponseStatusException parseException() {
        log.error("Error: Can't parse value");
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Parsing error");
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseStatusException parseAnyException() {
        log.error("Error: Unexpected error");
        return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error");
    }

}
