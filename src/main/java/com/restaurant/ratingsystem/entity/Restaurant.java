package com.restaurant.ratingsystem.entity;

import java.math.*;

public class Restaurant {
    private Long id;
    private String name;
    private String description;
    private String typeCuisine;
    private int aveCheck;
    private BigDecimal userRating;

    public Restaurant(Long id, String name, String description, String typeCuisine, int aveCheck, BigDecimal userRating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeCuisine = typeCuisine;
        this.aveCheck = aveCheck;
        this.userRating = userRating;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getTypeCuisine(){
        return typeCuisine;
    }

    public void setTypeCuisine(String typeCuisine){
        this.typeCuisine = typeCuisine;
    }

    public int getAveCheck(){
        return aveCheck;
    }

    public void setAveCheck(int aveCheck){
        this.aveCheck = aveCheck;
    }

    public BigDecimal getUserRating(){
        return userRating;
    }

    public void setUserRating(BigDecimal userRating){
        this.userRating = userRating;
    }
}