package com.pathmates.application.dto;

import java.util.List;
import java.util.Set;

import com.pathmates.application.entities.OtpVerification;
import com.pathmates.application.entities.PasswordResetToken;
import com.pathmates.application.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    private Set<Trip> trips;
    private List<OtpVerification> otpVerifications;
     private List<PasswordResetToken> passwordResetTokens;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
