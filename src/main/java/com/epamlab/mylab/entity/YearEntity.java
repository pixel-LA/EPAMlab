package com.epamlab.mylab.entity;


public class YearEntity {
    
    // private Long id;
    private int year;
    private String typeOfYear;
      
    public YearEntity() {
    }
    
    public YearEntity(int year, String typeOfYear) {
        this.year = year;
        this.typeOfYear = typeOfYear;
    }


    // public Long getId() {
    //     return id;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }

    public String getTypeOfYear() {
        return typeOfYear;
    }

    public void setTypeOfYear(String typeOfYear) {
        this.typeOfYear = typeOfYear;
    }
    
}