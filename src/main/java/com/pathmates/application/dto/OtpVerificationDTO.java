package com.pathmates.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.pathmates.application.entities.User;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpVerificationDTO {
    private String otpVerificationId;

    private User user;

    private String otp;

    private LocalDateTime expiresAt;

    private Boolean isVerified = false;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
