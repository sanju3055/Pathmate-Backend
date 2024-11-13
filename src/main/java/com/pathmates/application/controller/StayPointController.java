package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.StayPointDTO;
import com.pathmates.application.serviceimpl.StayPointServiceImpl;
import com.pathmates.application.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1/stay-point")
public class StayPointController {

    @Autowired
    private StayPointServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<StayPointDTO>> createStayPointDTO(@RequestBody StayPointDTO entity) {
        return new ResponseEntity<ApiResponse<StayPointDTO>>(serviceImpl.createStayPoint(entity), HttpStatus.CREATED);
    }

    @GetMapping("/:stayPointId")
    public ResponseEntity<ApiResponse<StayPointDTO>> getStayPointDTOById(@RequestParam String stayPointId) {
        return new ResponseEntity<>(serviceImpl.getStayPointById(stayPointId), HttpStatus.OK);
    }

    @PutMapping("/{stayPointId}")
    public ResponseEntity<ApiResponse<StayPointDTO>> updateStayPointDTO(@PathVariable String stayPointId,
            @RequestBody StayPointDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateStayPoint(stayPointId, entity), HttpStatus.OK);
    }

    @DeleteMapping("/{stayPointId}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String stayPointId) {
        return new ResponseEntity<>(serviceImpl.deleteStayPoint(stayPointId), HttpStatus.OK);
    }

}
