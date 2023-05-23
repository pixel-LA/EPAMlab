package com.epamlab.mylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.epamlab.mylab.entity.YearEntity;


@Repository
public interface YearRepository extends JpaRepository<YearEntity, Long> {

    YearEntity findByYear(Integer year);
    
}