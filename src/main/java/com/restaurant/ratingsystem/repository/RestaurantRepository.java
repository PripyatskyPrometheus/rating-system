package com.restaurant.ratingsystem.repository;

import com.restaurant.ratingsystem.entity.Restaurant;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private long nextId = 0;

    public synchronized Restaurant save(Restaurant restaurant) {
        restaurant.setId(nextId);
        nextId++;
        restaurants.add(restaurant);
        return restaurant;
    }

    public void remove(Long id) {
        restaurants.removeIf(r -> r.getId() != null &&  r.getId().equals(id));
    }

    public List<Restaurant> findAll() {
        return new ArrayList<>(restaurants);
    }

    public Optional<Restaurant> findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId() != null &&  r.getId().equals(id))
                .findFirst();
    }
}