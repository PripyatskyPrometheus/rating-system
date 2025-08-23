package com.restaurant.ratingsystem.controller;

import com.restaurant.ratingsystem.dto.RestaurantRequestDTO;
import com.restaurant.ratingsystem.dto.RestaurantResponseDTO;
import com.restaurant.ratingsystem.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@Tag(name = "Рестораны", description = "API для работы с ресторанами")
public class RestaurantController {
    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    @Operation(summary = "Получить все рестораны")
    public List<RestaurantResponseDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

        @GetMapping("/{id}")
    @Operation(summary = "Получить ресторан по ID")
    public RestaurantResponseDTO getRestaurant(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PostMapping
    @Operation(summary = "Создать новый ресторан")
    public RestaurantResponseDTO createRestaurant(@RequestBody RestaurantRequestDTO request) {
        return restaurantService.saveRestaurant(request);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновить данные ресторана")
    public RestaurantResponseDTO updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequestDTO request) {
        restaurantService.removeRestaurant(id);
        return restaurantService.saveRestaurant(request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить ресторан")
    public void deleteRestaurant(@PathVariable Long id) {
        restaurantService.removeRestaurant(id);
    }
}
