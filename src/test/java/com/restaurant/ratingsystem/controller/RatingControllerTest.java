package com.restaurant.ratingsystem.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import com.restaurant.ratingsystem.dto.RatingRequestDTO;
import com.restaurant.ratingsystem.dto.RatingResponseDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.ratingsystem.service.RatingVisitorService;

@WebMvcTest(RatingController.class)
public class RatingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RatingVisitorService ratingVisitorService;

    @Test   
    void addRating_ShouldReturnCreatedRating() throws Exception {
        RatingRequestDTO request = new RatingRequestDTO(1L, 1L, 5, "Отлично!");
        RatingResponseDTO response = new RatingResponseDTO(1L, 1L, 1L, 5, "Отлично!");
        
        when(ratingVisitorService.addRating(any(RatingRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/ratings")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(request)))
                 .andExpect(status().isOk())
                 .andExpect(jsonPath("$.id").value(1L));

         verify(ratingVisitorService).addRating(any(RatingRequestDTO.class));
    }

    @Test
    void getAllRatings_ShouldReturnRatings() throws Exception {
        RatingResponseDTO response = new RatingResponseDTO(1L, 1L, 1L, 5, "Отлично!");
        when(ratingVisitorService.getAllRatings()).thenReturn(java.util.List.of(response));

        mockMvc.perform(get("/api/ratings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].textReview").value("Отлично!"));

        verify(ratingVisitorService).getAllRatings();
    }

    @Test
    void deleteRating_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/ratings/1"))
                .andExpect(status().isOk());

        verify(ratingVisitorService).removeRating(1L);
    }
}