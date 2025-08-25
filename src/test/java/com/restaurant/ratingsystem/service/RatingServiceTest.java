package com.restaurant.ratingsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.restaurant.ratingsystem.dto.RatingRequestDTO;
import com.restaurant.ratingsystem.dto.RatingResponseDTO;
import com.restaurant.ratingsystem.entity.RatingVisitor;
import com.restaurant.ratingsystem.repository.RatingVisitorRepository;
import com.restaurant.ratingsystem.repository.RestaurantRepository;
import com.restaurant.ratingsystem.repository.VisitorRepository;
import com.restaurant.ratingsystem.entity.Visitor;
import com.restaurant.ratingsystem.entity.Restaurant;
import java.util.Optional;
import java.util.List;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RatingServiceTest {
    @Mock
    private RatingVisitorRepository ratingRepository;

    @Mock
    private VisitorRepository visitorRepository;

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private RatingVisitorService ratingService;

    @Test
    void addRating_ShouldReturnSavedRating() {
        RatingRequestDTO ratingRequest = new RatingRequestDTO(1L, 1L, 5, "Отличный ресторан!");

        Visitor visitor = new Visitor(1L, "John Doe", 30, "Мужской");
        Restaurant restaurant = new Restaurant(1L, "Pizza Place", "123 Main St", 
            "Итальянская", 600, null);

        RatingVisitor savedRating = new RatingVisitor(1L, visitor, restaurant, 5, "Отличный ресторан!");

        when(visitorRepository.findById(1L)).thenReturn(Optional.of(visitor));
        when(restaurantRepository.findById(1L)).thenReturn(Optional.of(restaurant));
    
        when(ratingRepository.save(any(RatingVisitor.class))).thenReturn(savedRating);
        when(ratingRepository.findAverageRatingByRestaurantId(1L)).thenReturn(5.0);

        RatingResponseDTO result = ratingService.addRating(ratingRequest);

        assertNotNull(result);
        assertEquals(1L, result.idVisitor());
        assertEquals(1L, result.idRestaurant());
        assertEquals(5, result.rating());
        assertEquals("Отличный ресторан!", result.textReview());

        verify(visitorRepository).findById(1L);
        verify(restaurantRepository, times(2)).findById(1L);
        verify(ratingRepository).save(any(RatingVisitor.class));
    }

    @Test
    void removeRating_ShouldCallDeleteById() {
        Long ratingId = 1L;
        Long restaurantId = 1L;

        Visitor visitor = new Visitor();
        Restaurant restaurant = new Restaurant(1L, "Pizza Place", "123 Main St", 
            "Итальянская", 600, null);
    
        RatingVisitor ratingToDelete = new RatingVisitor(ratingId, visitor, restaurant, 5, "Отлично!");

        when(ratingRepository.findAll()).thenReturn(List.of(ratingToDelete));
        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurant));
        when(ratingRepository.findAverageRatingByRestaurantId(restaurantId)).thenReturn(4.5);

        ratingService.removeRating(ratingId);

        verify(ratingRepository, times(1)).deleteById(ratingId);

        verify(ratingRepository, times(1)).findAverageRatingByRestaurantId(restaurantId);
        verify(restaurantRepository, times(1)).findById(restaurantId);
        verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    }

    @Test
    void getAllRatings_ShouldReturnList() {
        // Given
        when(ratingRepository.findAll()).thenReturn(List.of(
            new RatingVisitor(1L, new Visitor(), new Restaurant(), 5, "Хорошо"),
            new RatingVisitor(2L, new Visitor(), new Restaurant(), 4, "Нормально")
        ));

        List<RatingResponseDTO> result = ratingService.getAllRatings();

        assertEquals(2, result.size());
        verify(ratingRepository).findAll();
    }
}