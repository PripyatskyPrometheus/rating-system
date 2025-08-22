package com.restaurant.ratingsystem.dto;

public record RestaurantRequestDTO(
    String name,
    String description,
    String typeCuisine,
    int aveCheck
) {}