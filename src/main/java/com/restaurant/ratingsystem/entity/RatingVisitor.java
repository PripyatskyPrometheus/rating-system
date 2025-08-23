package com.restaurant.ratingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Rating_Visitor")
public class RatingVisitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visitor_id", nullable = false)
    Visitor visitor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    Restaurant restaurant;

    @Column(nullable = false)
    int rating;

    @Column(columnDefinition = "TEXT", nullable = false)
    String textReview;

    /*
    public RatingVisitor(Long id, Long idVisitor, Long idRestaurant, int rating, String textReview) {
        this.id = id;
        this.idVisitor = idVisitor;
        this.idRestaurant = idRestaurant;
        this.rating = rating;
        this.textReview = textReview;
    }

    public Long getId(){
        return id;
    }

    public Long getIdVisitor(){
        return idVisitor;
    }

    public Long getIdRestaurant(){
        return idRestaurant;
    }

    public int getRating(){
        return rating;
    }

    public String getTextReview(){
        return textReview;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setIdVisitor(Long idVisitor){
        this.idVisitor = idVisitor;
    }

    public void setIdRestaurant(Long idRestaurant){
        this.idRestaurant = idRestaurant;
    }

    public void setRating(int rating){
        this.rating = rating;
    }

    public void setTextReview(String textReview){
        this.textReview = textReview;
    } */
}