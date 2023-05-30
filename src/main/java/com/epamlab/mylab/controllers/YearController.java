package com.epamlab.mylab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epamlab.mylab.entity.CounterThreadEntity;
import com.epamlab.mylab.entity.YearEntity;
import com.epamlab.mylab.service.YearEntityLogic;
import com.epamlab.mylab.service.YearService;
import com.epamlab.mylab.service.cache.CacheService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
public class YearController {

    private static final Logger log = LoggerFactory.getLogger(YearController.class);

    private CacheService cache;

    @Autowired
    private YearService yearService;

    @Autowired
    public void setCache(CacheService cache) {
        this.cache = cache;
    }

    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully converted", content = {@Content(mediaType = "application/json")}), 
                       @ApiResponse(responseCode = "400", description = "The number is less than 1582", content = @Content), 
                       @ApiResponse(responseCode = "500", description = "Can't parse into double", content = @Content)})
    @GetMapping("/year")
    public ResponseEntity<?> getTypeOfYear(@RequestParam(name = "year", required = false, defaultValue = "") List<String> values) {
        
        CounterThreadEntity counter = new CounterThreadEntity();
        counter.start();
        
        log.info("Trying to parse data...");
        if (!values.stream().allMatch(YearEntityLogic::validateData)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        log.info("Parsing completed!");
        
        CompletableFuture<List<YearEntity>> answers = CompletableFuture.supplyAsync(() -> processData(values));
        answers.thenAccept(this::saveToDatabase);

        return new ResponseEntity<>(processData(values), HttpStatus.OK);
    }

    @PostMapping(value = "/bulkAnswers")
    public ResponseEntity<?> getBulkAnswers(@RequestBody String params) {

        JSONObject jsonRequest = new JSONObject(params);
        log.info("Trying to parse data...");

        List<String> values = jsonRequest.toMap().values().stream().map(Object::toString).toList();
        if (!values.stream().allMatch(YearEntityLogic::validateData)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        log.info("Parsing completed!");
       
        CompletableFuture<List<YearEntity>> answers = CompletableFuture.supplyAsync(() -> processData(values));
        answers.thenAccept(this::saveToDatabase);
        return new ResponseEntity<>(processData(values), HttpStatus.OK);
    }

    @GetMapping(value = "/getFromDB")
    public ResponseEntity<?> getFromDB(@RequestParam(value="id", defaultValue = "") Long value) {
        YearEntity yearEntity = yearService.getYearEntityById(value).orElse(null);
        if (yearEntity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(yearEntity, HttpStatus.OK);
    }

    public void saveToDatabase(List<YearEntity> answers) {
        for (YearEntity answer : answers) {
            yearService.findOrCreateByInput(answer.getYear());
        }
    }

    public List<YearEntity> processData(List<String> values) {
        List<Integer> valuesParsed = values.stream().map(YearEntityLogic::parseData).toList();
        return Stream.concat(   valuesParsed.stream().filter(value -> cache.contains(value)).map(value -> cache.getCache(value)),
                                valuesParsed.stream().filter(value -> !cache.contains(value)).map(value -> {YearEntity yearEntity = new YearEntity();
                                                                                                            YearEntityLogic.setAll(yearEntity, value);
                                                                                                            cache.putCache(yearEntity);
                                                                                                            return yearEntity;
                                                                                                            })
                            ).sorted().toList();
    }
}