package com.restaurant.ratingsystem.repository;

import com.restaurant.ratingsystem.entity.RatingVisitor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

@Repository
public interface RatingVisitorRepository extends JpaRepository<RatingVisitor, Long> {
    @Query("SELECT AVG(r.rating) FROM RatingVisitor r WHERE r.restaurant.id = :restaurantId")
    Double findAverageRatingByRestaurantId(@Param("restaurantId") Long restaurantId);
    
    List<RatingVisitor> findByRestaurantId(Long restaurantId);
    
    List<RatingVisitor> findByVisitorId(Long visitorId);
}

/* public class RatingVisitorRepository {
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
}*/