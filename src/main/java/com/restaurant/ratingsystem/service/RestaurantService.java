package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Restaurant;
import com.restaurant.ratingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant saveRestaurant(String name, String description, String typeCuisine, int aveCheck) {
        Restaurant restaurant = new Restaurant(null, name, description, typeCuisine, aveCheck, BigDecimal.ZERO);
        return restaurantRepository.save(restaurant);
    }

    public void removeRestaurant(Long id) {
        restaurantRepository.remove(id);
    }

    public List<Restaurant> getAllRestaurants() {
        return  restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ресторан не найден"));
    }
}