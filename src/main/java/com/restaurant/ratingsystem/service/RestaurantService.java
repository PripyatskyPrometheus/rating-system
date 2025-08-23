package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Restaurant;
import com.restaurant.ratingsystem.dto.RestaurantResponseDTO;
import com.restaurant.ratingsystem.dto.RestaurantRequestDTO;
import com.restaurant.ratingsystem.repository.RestaurantRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    //public Restaurant saveRestaurant(String name, String description, String typeCuisine, int aveCheck) {
    //    Restaurant restaurant = new Restaurant(null, name, description, typeCuisine, aveCheck, BigDecimal.ZERO);
    //    return restaurantRepository.save(restaurant);
    //}

    public RestaurantResponseDTO saveRestaurant(RestaurantRequestDTO restaurantRequestDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequestDTO.name());
        restaurant.setDescription(restaurantRequestDTO.description());
        restaurant.setTypeCuisine(restaurantRequestDTO.typeCuisine());
        restaurant.setAveCheck(restaurantRequestDTO.aveCheck());
        restaurant.setUserRating(BigDecimal.ZERO);
        restaurant = restaurantRepository.save(restaurant);
        return convertToResponse(restaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    // public List<Restaurant> getAllRestaurants() {
    //    return  restaurantRepository.findAll();
    //}

    //public Restaurant getRestaurantById(Long id) {
    //    return restaurantRepository.findById(id)
    //            .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
    //}

    public List<RestaurantResponseDTO> getAllRestaurants() {
        return  restaurantRepository.findAll().stream()
            .map(this::convertToResponse)
            .toList();
    }

    //public Restaurant getRestaurantById(Long id) {
    //    return restaurantRepository.findById(id)
    //           .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
    //}

    public RestaurantResponseDTO getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
        return convertToResponse(restaurant);
    }

    private RestaurantResponseDTO convertToResponse(Restaurant restaurant) {
        return new RestaurantResponseDTO(restaurant.getId(), restaurant.getName(), 
            restaurant.getDescription(), restaurant.getTypeCuisine(),
            restaurant.getAveCheck(), restaurant.getUserRating());
    }
}