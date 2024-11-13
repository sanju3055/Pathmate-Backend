package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.UserDTO;
import com.pathmates.application.serviceimpl.UserServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private UserServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@RequestBody UserDTO entity) {
        return new ResponseEntity<>(serviceImpl.createUser(entity), HttpStatus.CREATED);
    }

    @GetMapping("/:userId")
    public ResponseEntity<ApiResponse<UserDTO>> getUser(@RequestParam String userId) {
        return new ResponseEntity<>(serviceImpl.getUserById(userId), HttpStatus.OK);
    }
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserDTO>> updateUser(@PathVariable String userId, @RequestBody UserDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateUser(userId, entity), HttpStatus.OK);
    }

}
