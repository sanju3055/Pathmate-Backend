package com.pathmates.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.TripDTO;
import com.pathmates.application.serviceimpl.TripServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/trip")
public class TripController {

    @Autowired
    private TripServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<TripDTO>> createTrip(@RequestBody TripDTO entity) {
        return new ResponseEntity<>(serviceImpl.createTrip(entity), HttpStatus.CREATED);
    }

    @GetMapping("/:groupId")
    public ResponseEntity<ApiResponse<TripDTO>> getTrip(@RequestParam String groupId) {
        return new ResponseEntity<>(serviceImpl.getTripById(groupId), HttpStatus.OK);
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<ApiResponse<TripDTO>> updateTrip(@PathVariable String groupId, @RequestBody TripDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateTrip(groupId, entity), HttpStatus.OK);
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<ApiResponse<String>> deleteTrip(@PathVariable String groupId) {
        return new ResponseEntity<>(serviceImpl.deleteTrip(groupId), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ApiResponse<List<TripDTO>>> getMethodName() {
        return new ResponseEntity<>(serviceImpl.getTrips(), HttpStatus.OK);
    }

}
