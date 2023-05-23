package com.epamlab.mylab.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "years")
public class YearEntity implements Comparable<YearEntity> {
    
    @Id
    private Long id;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "typeOfString", nullable = false)
    private String typeOfYear;
      
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getYear() {
        return year;
    }
    
    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTypeOfYear() {
        return typeOfYear;
    }

    public void setTypeOfYear(String typeOfYear) {
        this.typeOfYear = typeOfYear;
    }   

    @Override
    public int compareTo(YearEntity yearEntity) {
        return this.getYear()
                .compareTo(yearEntity.getYear());
    }

    @Override
    public String toString() {
        return "Converter{" + ", input=" + year
                +  ", typeOfYear=" + typeOfYear + '}';
    }
}