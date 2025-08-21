package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Restaurant;
import com.restaurant.ratingsystem.entity.RatingVisitor;
import com.restaurant.ratingsystem.repository.RestaurantRepository;
import com.restaurant.ratingsystem.repository.RatingVisitorRepository;

import org.springframework.stereotype.Service;

import java.math.*;
import java.util.*;

@Service
public class RatingVisitorService {
    private final RatingVisitorRepository ratingVisitorRepository;
    private final RestaurantRepository restaurantRepository;

    public RatingVisitorService(RatingVisitorRepository ratingVisitorRepository, RestaurantRepository restaurantRepository) {
        this.ratingVisitorRepository = ratingVisitorRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public RatingVisitor addRating(Long visitorId, Long restaurantId, int rating, String textReview) {
        RatingVisitor object = new RatingVisitor(null, visitorId, restaurantId, rating, textReview);
        ratingVisitorRepository.save(object);

        updateRestaurantRating(restaurantId);
        return object;
    }

        private void updateRestaurantRating(Long restaurantId) {
        List<RatingVisitor> restaurantRatings = ratingVisitorRepository.findAll().stream()
                .filter(r -> r.getIdRestaurant().equals(restaurantId))
                .toList();

        if (!restaurantRatings.isEmpty()) {
            double average = restaurantRatings.stream()
                    .mapToInt(RatingVisitor::getRating)
                    .average()
                    .orElse(0.0);
            
            BigDecimal averageRating = BigDecimal.valueOf(average)
                    .setScale(2, RoundingMode.HALF_UP);
            
            Restaurant restaurant = restaurantRepository.findById(restaurantId)
                    .orElseThrow(() -> new RuntimeException("Ресторан не найден"));

            restaurant.setUserRating(averageRating);
        }
    }

    public void removeRating(Long id) {
        RatingVisitor rating = ratingVisitorRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Оценка не найдена"));
        
        ratingVisitorRepository.remove(id);
        updateRestaurantRating(rating.getIdRestaurant());
    }

    public List<RatingVisitor> getAllRatings() {
        return ratingVisitorRepository.findAll();
    }
}