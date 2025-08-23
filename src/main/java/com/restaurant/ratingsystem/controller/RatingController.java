package com.restaurant.ratingsystem.controller;

import com.restaurant.ratingsystem.dto.RatingRequestDTO;
import com.restaurant.ratingsystem.dto.RatingResponseDTO;
import com.restaurant.ratingsystem.service.RatingVisitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Оценки", description = "API для работы с оценками")
public class RatingController {
    private final RatingVisitorService ratingVisitorService;

    public RatingController(RatingVisitorService ratingVisitorService) {
        this.ratingVisitorService = ratingVisitorService;
    }

    @PostMapping
    @Operation(summary = "Добавить оценку ресторану")
    public RatingResponseDTO addRating(@RequestBody RatingRequestDTO request) {
        return ratingVisitorService.addRating(request);
    }
    /*@PostMapping
    @Operation(summary = "Добавить новую оценку")
    public RatingResponseDTO createRating(@RequestBody RatingRequestDTO request) {
        return ratingVisitorService.addRating(request);
    } */
    
    
    @GetMapping
    @Operation(summary = "Получить все оценки")
    public List<RatingResponseDTO> getAllRatings() {
        return ratingVisitorService.getAllRatings();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить оценку")
    public RatingResponseDTO updateRating(@PathVariable Long id, @RequestBody RatingRequestDTO request) {
        ratingVisitorService.removeRating(id);
        return ratingVisitorService.addRating(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить оценку")
    public void deleteRating(@PathVariable Long id) {
        ratingVisitorService.removeRating(id);
    }
}