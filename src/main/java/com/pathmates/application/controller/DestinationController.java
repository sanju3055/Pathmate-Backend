package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.DestinationDTO;
import com.pathmates.application.serviceimpl.DestinationServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping(value = "/api/v1/destination")
public class DestinationController {

    @Autowired
    private DestinationServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<DestinationDTO>> createDestination(@RequestBody DestinationDTO entity) {
        return new ResponseEntity<ApiResponse<DestinationDTO>>(serviceImpl.createDestination(entity), HttpStatus.OK);
    }

    @GetMapping("/:destinationId")
    public ResponseEntity<ApiResponse<DestinationDTO>> getDestinationById(@RequestParam String destinationId) {
        return new ResponseEntity<>(serviceImpl.getDestinationById(destinationId), HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DestinationDTO>> updateDestination(@PathVariable String id, @RequestBody DestinationDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateDestination(id, entity), HttpStatus.OK);
    }
    
}
