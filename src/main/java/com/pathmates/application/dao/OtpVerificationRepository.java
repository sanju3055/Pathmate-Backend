package com.pathmates.application.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pathmates.application.entities.OtpVerification;
import com.pathmates.application.entities.User;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, String> {
    Optional<OtpVerification> findFirstByUserOrderByExpiresAtDesc(User user);

}
