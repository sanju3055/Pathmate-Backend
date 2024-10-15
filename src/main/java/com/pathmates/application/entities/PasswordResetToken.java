package com.pathmates.application.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "password_reset_token")
public class PasswordResetToken extends Auditable {

    @Id
    @UuidGenerator
    private String passwordResetTokenId;

    private String token;

    private String password;

    private LocalDateTime expiresAt;

    @Column(nullable = false)
    private Boolean isVerified = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
