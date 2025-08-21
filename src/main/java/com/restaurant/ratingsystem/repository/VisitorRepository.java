package com.restaurant.ratingsystem.repository;

import com.restaurant.ratingsystem.entity.Visitor;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class VisitorRepository {
    private final List<Visitor> visitors = new ArrayList<>();
    private long nextId = 0;

    public Visitor save(Visitor visitor) {
        visitor.setId(nextId);
        nextId++;
        visitors.add(visitor);
        return visitor;
    }

    public void remove(Long id) {
        visitors.removeIf(v -> v.getId() != null && v.getId().equals(id));
    }

    public List<Visitor> findAll() {
        return new ArrayList<>(visitors);
    }
}