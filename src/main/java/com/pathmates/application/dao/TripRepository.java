package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.Trip;

public interface TripRepository extends JpaRepository<Trip, String> {

}
