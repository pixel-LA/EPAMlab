package com.epamlab.mylab.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.epamlab.mylab.service.YearService;


@SpringBootTest
public class YearControllerTest {

    @Mock
    public YearService yearService = new YearService();

    @Spy
    @InjectMocks
    private YearController yearController;

    @BeforeEach
    void setMockOutput() {
        when(yearService.yearTypeDefinition(2000)).thenReturn(null);
    }

    @Test
    public void shouldReturnTypeOfYear() {
        Assertions.assertNotNull(yearController.getTypeOfYear("2000").getStatusCode());
        Assertions.assertEquals(yearController.getTypeOfYear("2000").getStatusCode(), ResponseEntity.ok().body(null).getStatusCode());
        Assertions.assertEquals(yearController.getTypeOfYear("1582").getStatusCode(), ResponseEntity.ok().body(null).getStatusCode());
        Assertions.assertEquals(yearController.getTypeOfYear("2000qw").getStatusCode(), ResponseEntity.badRequest().body(null).getStatusCode());
        Assertions.assertEquals(yearController.getTypeOfYear("1581").getStatusCode(), ResponseEntity.internalServerError().body(null).getStatusCode());
    }
}
