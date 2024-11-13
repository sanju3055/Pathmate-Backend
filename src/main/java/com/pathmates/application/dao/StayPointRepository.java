package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.StayPoint;

public interface StayPointRepository extends JpaRepository<StayPoint, String> {

}
