package com.restaurant.ratingsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;  
import org.springframework.http.MediaType;
import com.restaurant.ratingsystem.dto.RestaurantRequestDTO;
import com.restaurant.ratingsystem.dto.RestaurantResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.ratingsystem.service.RestaurantService;

import java.util.List;
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RestaurantService restaurantService;

    @Test
    void createRestaurant_ShouldReturnCreatedRestaurant() throws Exception {
        RestaurantRequestDTO request = new RestaurantRequestDTO("Pizza Place", "123 Main St", "Итальянская", 600);
        RestaurantResponseDTO response = new RestaurantResponseDTO(1L, "Pizza Place", "123 Main St", "Итальянская", 600, BigDecimal.ZERO);
        
        when(restaurantService.saveRestaurant(any(RestaurantRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));

        verify(restaurantService).saveRestaurant(any(RestaurantRequestDTO.class));
    }

    @Test
    void getAllRestaurants_ShouldReturnRestaurants() throws Exception {
        RestaurantResponseDTO response = new RestaurantResponseDTO(1L, "Pizza Place", "123 Main St", 
            "Итальянская", 600, BigDecimal.ZERO);
        when(restaurantService.getAllRestaurants()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/restaurants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Pizza Place"));

        verify(restaurantService).getAllRestaurants();
    }

    @Test
    void getRestaurantById_ShouldReturnRestaurant() throws Exception {
        RestaurantResponseDTO response = new RestaurantResponseDTO(1L, "Pizza Place", "123 Main St", 
            "Итальянская", 600, BigDecimal.ZERO);
        when(restaurantService.getRestaurantById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/restaurants/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pizza Place"))
                .andExpect(jsonPath("$.typeCuisine").value("Итальянская"));;

        verify(restaurantService).getRestaurantById(1L);
    }

    @Test
    void deleteRestaurant_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/restaurants/1"))
                .andExpect(status().isOk());

        verify(restaurantService).removeRestaurant(1L);
    }
}