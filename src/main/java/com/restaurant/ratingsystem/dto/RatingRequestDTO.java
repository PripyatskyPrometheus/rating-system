package com.restaurant.ratingsystem.dto;

public record RatingRequestDTO(
    Long idVisitor,
    Long idRestaurant,
    int rating,
    String textReview
) {}