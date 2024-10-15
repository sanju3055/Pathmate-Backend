package com.pathmates.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.PasswordResetToken;
import com.pathmates.application.entities.User;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, String> {
    Optional<PasswordResetToken> findFirstByUserOrderByExpiresAtDesc(User user);
}
