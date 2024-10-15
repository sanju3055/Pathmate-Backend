package com.pathmates.application.dto;

import java.time.Instant;
import java.time.LocalDateTime;

import com.pathmates.application.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetTokenDTO {
    private String passwordResetTokenId;

    private String token;

    private String password;

    private User user;

    private LocalDateTime expiresAt;
    private Boolean isVerified;

    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;

}
