package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Restaurant;
import com.restaurant.ratingsystem.entity.Visitor;
import com.restaurant.ratingsystem.entity.RatingVisitor;
import com.restaurant.ratingsystem.dto.RatingRequestDTO;
import com.restaurant.ratingsystem.dto.RatingResponseDTO;
import com.restaurant.ratingsystem.repository.RestaurantRepository;
import com.restaurant.ratingsystem.repository.VisitorRepository;

import jakarta.transaction.Transactional;

import com.restaurant.ratingsystem.repository.RatingVisitorRepository;

import org.springframework.stereotype.Service;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingVisitorService {
    private final RatingVisitorRepository ratingVisitorRepository;
    private final RestaurantRepository restaurantRepository;
    private final VisitorRepository visitorRepository;

    public RatingVisitorService(RatingVisitorRepository ratingVisitorRepository, 
                                RestaurantRepository restaurantRepository,
                                VisitorRepository visitorRepository) {
        this.ratingVisitorRepository = ratingVisitorRepository;
        this.restaurantRepository = restaurantRepository;
        this.visitorRepository = visitorRepository;
    }

    //public RatingVisitor addRating(Long visitorId, Long restaurantId, int rating, String textReview) {
    //    RatingVisitor object = new RatingVisitor(null, visitorId, restaurantId, rating, textReview);
    //    ratingVisitorRepository.save(object);
    //    updateRestaurantRating(restaurantId);
    //    return object;
    //}

    public RatingResponseDTO addRating(RatingRequestDTO ratingRequestDTO) {
        Visitor visitor = visitorRepository.findById(ratingRequestDTO.idVisitor())
                .orElseThrow(() -> new RuntimeException("Посетитель не найден"));
        
        Restaurant restaurant = restaurantRepository.findById(ratingRequestDTO.idRestaurant())
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
        
        RatingVisitor rating =new RatingVisitor(
            null,
            visitor,
            restaurant,
            ratingRequestDTO.rating(),
            ratingRequestDTO.textReview()
        );
        
        rating = ratingVisitorRepository.save(rating);
        
        updateRestaurantRating(restaurant.getId());
        
        return convertToResponse(rating);
    }

    private void updateRestaurantRating(Long idRestaurant) {
        Double aveRating = ratingVisitorRepository.findAverageRatingByRestaurantId(idRestaurant);
        
        if (aveRating != null) {
            BigDecimal roundedRating = BigDecimal.valueOf(aveRating)
                    .setScale(2, RoundingMode.HALF_UP);
            
            Restaurant restaurant = restaurantRepository.findById(idRestaurant)
                    .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
            
            restaurant.setUserRating(roundedRating);
            restaurantRepository.save(restaurant);
        }
    }

    public void removeRating(Long id) {
        RatingVisitor rating = ratingVisitorRepository.findAll().stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Оценка не найдена"));

        ratingVisitorRepository.deleteById(id);
        updateRestaurantRating(rating.getRestaurant().getId());
    }

    //public List<RatingVisitor> getAllRatings() {
    //    return ratingVisitorRepository.findAll();
    //}

    public List<RatingResponseDTO> getAllRatings() {
        return ratingVisitorRepository.findAll().stream()
            .map(this::convertToResponse)
            .collect(Collectors.toList());
    }

    private RatingResponseDTO convertToResponse(RatingVisitor rating) {
        return new RatingResponseDTO(
            rating.getId(),
            rating.getVisitor().getId(),
            rating.getRestaurant().getId(),
            rating.getRating(),
            rating.getTextReview()
        );
    }
}