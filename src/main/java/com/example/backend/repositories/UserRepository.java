package com.example.backend.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.backend.entities.User;


// Define SQL queries using JPA;
// See: https://www.bezkoder.com/jpa-repository-query
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findById(UUID id); // Find user if exists
    Optional<User> findByEmail(String email); 
}
