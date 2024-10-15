package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.Location;

public interface LocationRepository extends JpaRepository<Location, String> {

}
