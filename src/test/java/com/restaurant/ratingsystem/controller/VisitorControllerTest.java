package com.restaurant.ratingsystem.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;
import com.restaurant.ratingsystem.dto.VisitorRequestDTO;
import com.restaurant.ratingsystem.dto.VisitorResponseDTO;

import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.ratingsystem.service.VisitorService;

@WebMvcTest(VisitorController.class)
public class VisitorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private VisitorService visitorService;

    @Test
    void createVisitor_ShouldReturnCreatedVisitor() throws Exception {
        VisitorRequestDTO request = new VisitorRequestDTO("Иван", 25, "М");
        VisitorResponseDTO response = new VisitorResponseDTO(1L, "Иван", 25, "М");
        
        when(visitorService.saveVisitor(any(VisitorRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/visitors")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getAllVisitors_ShouldReturnVisitors() throws Exception {
        VisitorResponseDTO response = new VisitorResponseDTO(1L, "Иван", 25, "М");
        when(visitorService.getAllVisitors()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/visitors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Иван"));
    }

    @Test
    void deleteVisitor_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/visitors/1"))
                .andExpect(status().isOk());
    }
}