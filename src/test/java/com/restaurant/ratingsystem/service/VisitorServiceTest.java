package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Visitor;
import com.restaurant.ratingsystem.repository.VisitorRepository;
import com.restaurant.ratingsystem.dto.VisitorRequestDTO;
import com.restaurant.ratingsystem.dto.VisitorResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceTest {
    @Mock
    private VisitorRepository visitorRepository;

    @InjectMocks
    private VisitorService visitorService;

    @Test
    void saveVisitor_ShouldReturnSavedVisitor() {
        VisitorRequestDTO visitor = new VisitorRequestDTO("John Doe", 30, "Мужской");
        Visitor savedVisitor = new Visitor(1L, "John Doe", 30, "Мужской");

        when(visitorRepository.save(any(Visitor.class))).thenReturn(savedVisitor);
       VisitorResponseDTO result = visitorService.saveVisitor(visitor);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("John Doe", result.name());
        assertEquals(30, result.age());
        assertEquals("Мужской", result.gender());
        verify(visitorRepository, times(1)).save(any(Visitor.class));
    }

    @Test
    void getAllVisitors_ShouldReturnListOfVisitors() {
        List<Visitor> visitors = List.of(
            new Visitor(1L, "John Doe", 30, "Мужской"),
            new Visitor(2L, "Jane Smith", 25, "Женский")
        );

        when(visitorRepository.findAll()).thenReturn(visitors);
        List<VisitorResponseDTO> result = visitorService.getAllVisitors();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).name());
        assertEquals("Jane Smith", result.get(1).name());
        verify(visitorRepository, times(1)).findAll();
    }

    @Test
    void removeVisitor_ShouldCallDeleteById() {
        visitorService.removeVisitor(1L);
        verify(visitorRepository, times(1)).deleteById(1L);
    }
}