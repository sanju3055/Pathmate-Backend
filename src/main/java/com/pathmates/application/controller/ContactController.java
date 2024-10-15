package com.pathmates.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.ContactDTO;
import com.pathmates.application.serviceimpl.ContactServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired
    private ContactServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<ContactDTO>> createContact(@RequestBody ContactDTO entity) {
        return new ResponseEntity<ApiResponse<ContactDTO>>(serviceImpl.createContact(entity), HttpStatus.CREATED);
    }

    @PostMapping("/save-all")
    public ResponseEntity<ApiResponse<List<ContactDTO>>> createAllContact(@RequestBody List<ContactDTO> entities) {
        return new ResponseEntity<ApiResponse<List<ContactDTO>>>(serviceImpl.createAllContact(entities),
                HttpStatus.CREATED);
    }

    @GetMapping("/:contactId")
    public ResponseEntity<ApiResponse<ContactDTO>> getContactById(@RequestParam String contactId) {
        return new ResponseEntity<ApiResponse<ContactDTO>>(serviceImpl.getContactById(contactId), HttpStatus.OK);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ApiResponse<ContactDTO>> updateContact(@PathVariable String contactId,
            @RequestBody ContactDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateContact(contactId, entity), HttpStatus.OK);
    }
}
