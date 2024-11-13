package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination, String> {

}
