package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.dto.VisitorRequestDTO;
import com.restaurant.ratingsystem.dto.VisitorResponseDTO;
import com.restaurant.ratingsystem.entity.Visitor;
import com.restaurant.ratingsystem.repository.VisitorRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    //public Visitor saveVisitor(String name, int age, String gender) {
    //    Visitor visitor = new Visitor(null, name, age, gender);
    //    return visitorRepository.save(visitor);
    //}

    public VisitorResponseDTO saveVisitor(VisitorRequestDTO visitorRequestDTO) {
        Visitor visitor = new Visitor();
        visitor.setName(visitorRequestDTO.name());
        visitor.setAge(visitorRequestDTO.age());
        visitor.setGender(visitorRequestDTO.gender());
        visitor = visitorRepository.save(visitor);
        return convertToResponse(visitor);
    }

    public void removeVisitor(Long id) {
        visitorRepository.deleteById(id);
    }

    //public List<Visitor> getAllVisitors() {
    //    return  visitorRepository.findAll();
    //}

    public List<VisitorResponseDTO> getAllVisitors() {
        return visitorRepository.findAll().stream()
            .map(this::convertToResponse)
            .toList();
    }

    public Visitor getVisitorById(Long id) {
        return visitorRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Посетитель не найден"));
    }

    private VisitorResponseDTO convertToResponse(Visitor visitor) {
        return new VisitorResponseDTO(visitor.getId(), visitor.getName(), visitor.getAge(), visitor.getGender());
    }
}