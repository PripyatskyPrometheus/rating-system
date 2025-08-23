package com.restaurant.ratingsystem.repository;

import com.restaurant.ratingsystem.entity.Visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {}
/*public class VisitorRepository {
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
} */