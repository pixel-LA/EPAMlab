package com.epamlab.mylab.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epamlab.mylab.entity.YearEntity;
import com.epamlab.mylab.entity.YearEntityLogic;
import com.epamlab.mylab.repository.YearRepository;

@Service
public class YearService {

    private static final Logger log = LoggerFactory.getLogger(YearService.class);

    @Autowired
    private YearRepository repository;

    @Autowired
    public YearService(YearRepository yearRepository) {
        this.repository = yearRepository;
    }

    public YearEntity findOrCreateByInput(Integer input) {
        log.info("save data");
        YearEntity yearEntity = repository.findByYear(input);
        if (yearEntity == null) {
            log.info("check data");
            yearEntity = new YearEntity();
            YearEntityLogic.setAll(yearEntity, input);
            yearEntity = repository.save(yearEntity);
            log.info("data save");
        }
        return yearEntity;
    }
    
    public YearEntity getYearType(Integer input) {
        return repository.findByYear(input);
    }

    public Optional<YearEntity> getYearEntityById(Long id) {
        return repository.findById(id);
    }

    public void deleteYearEntity(YearEntity yearEntity) {
        repository.delete(yearEntity);
    }

    public Iterable<YearEntity> getAllYears() {
        return repository.findAll();
    }
}