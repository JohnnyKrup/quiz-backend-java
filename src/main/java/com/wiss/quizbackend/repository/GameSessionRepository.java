package com.wiss.quizbackend.repository;

import com.wiss.quizbackend.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRepository
        extends JpaRepository<GameSession, Long> {

    // Spring Boot generiert automatisch:
    // - save(GameSession) - CREATE/UPDATE
    // - findById(Long id) - READ by ID
    // - findAll() - READ all
    // - deleteById(Long id) - DELETE
    // - count() - COUNT
    // ... und viele mehr!

    // Custom Queries (werden später gebraucht)
    List<GameSession> findByUserId(Long userId);
    List<GameSession> findByCategory(String category);
}
