package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dto.OtpVerificationDTO;
import com.pathmates.application.serviceimpl.OtpVerificationServiceImpl;
import com.pathmates.application.utils.ApiResponse;

@RestController
@RequestMapping("/api/v1/otp-verification")
public class OtpVerificationController {

    @Autowired
    private OtpVerificationServiceImpl serviceImpl;

    @PostMapping("")
    public ResponseEntity<ApiResponse<OtpVerificationDTO>> createLocation(@RequestBody OtpVerificationDTO entity) {
        return new ResponseEntity<ApiResponse<OtpVerificationDTO>>(serviceImpl.createOtpVerificationDTO(entity),
                HttpStatus.CREATED);
    }

    @GetMapping("/:otpVerificationId")
    public ResponseEntity<ApiResponse<OtpVerificationDTO>> getLocationById(@RequestParam String otpVerificationId) {
        return new ResponseEntity<>(serviceImpl.getOtpVerificationDTOById(otpVerificationId), HttpStatus.OK);
    }

    @PutMapping("/{otpVerificationId}")
    public ResponseEntity<ApiResponse<OtpVerificationDTO>> updateLocation(@PathVariable String otpVerificationId,
            @RequestBody OtpVerificationDTO entity) {
        return new ResponseEntity<>(serviceImpl.updateOtpVerificationDTO(otpVerificationId, entity), HttpStatus.OK);
    }
}
