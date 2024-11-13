package com.pathmates.application.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.Trip;
import com.pathmates.application.entities.User;

public interface TripRepository extends JpaRepository<Trip, String> {
    List<Trip> findByUser(User user);
}
