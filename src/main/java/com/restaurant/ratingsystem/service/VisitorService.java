package com.restaurant.ratingsystem.service;

import com.restaurant.ratingsystem.entity.Visitor;
import com.restaurant.ratingsystem.repository.VisitorRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VisitorService {
    private final VisitorRepository visitorRepository;

    public VisitorService(VisitorRepository visitorRepository) {
        this.visitorRepository = visitorRepository;
    }

    public Visitor saveVisitor(String name, int age, String gender) {
        Visitor visitor = new Visitor(null, name, age, gender);
        return visitorRepository.save(visitor);
    }

    public void removeVisitor(Long id) {
        visitorRepository.remove(id);
    }

    public List<Visitor> getAllVisitors() {
        return visitorRepository.findAll();
    }
}