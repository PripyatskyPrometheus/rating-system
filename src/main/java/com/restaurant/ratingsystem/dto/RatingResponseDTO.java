package com.restaurant.ratingsystem.dto;

public record RatingResponseDTO(
    Long id,
    Long idVisitor,
    Long idRestaurant,
    int rating,
    String textReview
) {}