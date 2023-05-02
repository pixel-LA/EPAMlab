package com.epamlab.mylab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epamlab.mylab.exception.CalendarRelevanceException;
import com.epamlab.mylab.service.YearService;


@RestController
@RequestMapping
public class YearController {
    
    @Autowired
    private YearService yearService;

    @GetMapping("/year")
    public ResponseEntity getTypeOfYear(@RequestParam(name = "year", required = false, defaultValue = "") String year) {
        try {
            return ResponseEntity.ok(yearService.yearTypeDefinition(Integer.parseInt(year)));
        } catch (CalendarRelevanceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } 
        
        // catch (NumberFormatException e) {
        //     return ResponseEntity.badRequest().body("Проверьте правильность введенных данных.");
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Упс... 500 ОШИБКА");
        }
    }
    
}
