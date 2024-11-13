package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.LocationDTO;
import com.pathmates.application.serviceimpl.LocationServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    @Autowired
    private LocationServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<LocationDTO>> createLocation(@RequestBody LocationDTO entity) {
       return new ResponseEntity<ApiResponse<LocationDTO>>(serviceImpl.createLocation(entity), HttpStatus.CREATED);
    }
    
    @GetMapping("/:locationId")
    public ResponseEntity<ApiResponse<LocationDTO>> getLocationById(@RequestParam String locationId) {
        return new ResponseEntity<>(serviceImpl.getLocationById(locationId), HttpStatus.OK);
    }
    
    @PutMapping("/{locationId}")
    public ResponseEntity<ApiResponse<LocationDTO>> updateLocation(@PathVariable String locationId, @RequestBody LocationDTO entity) {
       return new ResponseEntity<>(serviceImpl.updateLocation(locationId, entity), HttpStatus.OK);
    }
    
}
