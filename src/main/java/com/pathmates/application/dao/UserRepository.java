package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
