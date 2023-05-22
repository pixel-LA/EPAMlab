package com.epamlab.mylab.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.epamlab.mylab.entity.CounterEntity;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CounterController {

    private static final Logger log = LoggerFactory.getLogger(YearController.class);

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Got amount of entries", content = {@Content(mediaType = "application/json")})})
    @GetMapping(value = "/counter")
    public ResponseEntity<?> getCounter() {
        log.info("Get counter");
        return new ResponseEntity<>(CounterEntity.getCounter(), HttpStatus.OK);
    }
}
