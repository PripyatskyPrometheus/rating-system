package com.restaurant.ratingsystem.repository;

import com.restaurant.ratingsystem.entity.RatingVisitor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class RatingVisitorRepository {
    private final List<RatingVisitor> ratings = new ArrayList<>();
    private long nextId = 0;

    public RatingVisitor save(RatingVisitor rating) {
        rating.setId(nextId);
        nextId++;
        ratings.add(rating);
        return rating;
    }

    public void remove(Long id) {
        ratings.removeIf(r -> r.getId() != null && r.getId().equals(id));
    }

    public List<RatingVisitor> findAll() {
        return new ArrayList<>(ratings);
    }
}