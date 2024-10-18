package com.pathmates.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;

import com.pathmates.application.config.EmailService;
import com.pathmates.application.config.UserDetailService;
import com.pathmates.application.dao.OtpVerificationRepository;
import com.pathmates.application.dao.PasswordResetTokenRepository;
import com.pathmates.application.dao.UserRepository;
import com.pathmates.application.dto.OtpVerificationDTO;
import com.pathmates.application.dto.PasswordResetTokenDTO;
import com.pathmates.application.dto.UserDTO;
import com.pathmates.application.entities.OtpVerification;
import com.pathmates.application.entities.PasswordResetToken;
import com.pathmates.application.entities.User;
import com.pathmates.application.mapper.UserMapper;
import com.pathmates.application.utils.ApiResponse;
import com.pathmates.application.utils.AuthRequest;
import com.pathmates.application.utils.AuthResponse;
import com.pathmates.application.utils.JwtService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailService userDetailsService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpVerificationRepository otpVerificationRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserMapper mapper;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse<AuthResponse>> authenticate(@RequestBody AuthRequest entity) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(entity.getEmail(), entity.getPassword()));

            if (authentication.isAuthenticated() && authentication != null) {

                Random random = new Random();
                int otp = 100000 + random.nextInt(900000);
                Context context = new Context();
                context.setVariable("name", entity.getEmail());
                context.setVariable("email", entity.getEmail());
                context.setVariable("message", "Pathmates OTP Verification." + "Your OTP is: " + otp);
                context.setVariable("subject", "Pathmates Security Code");
                context.setVariable("otp", otp);
                emailService.sentMail("", entity.getEmail(), "Pathmates Security Code",
                        "EmailOtpTemplate", context);
                OtpVerification otpVerification = new OtpVerification();
                otpVerification.setUser(repository.findByEmail(entity.getEmail()));
                ;
                otpVerification.setOtp(String.valueOf(otp));
                LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);
                otpVerification.setExpiresAt(expirationTime);
                otpVerificationRepository.save(otpVerification);
                return new ResponseEntity<>(new ApiResponse<>(true, "Otp is sent to register email.", null, null),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid email or password", null, null),
                    HttpStatus.UNAUTHORIZED);

        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BadCredentialsException) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Invalid email or password", null, null),
                        HttpStatus.UNAUTHORIZED);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error", null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<String>> verifyEmail(@RequestBody OtpVerificationDTO entity) {
        try {
            User user = repository.findByEmail(entity.getUser().getEmail());
            if (user == null) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Email not found", null, null),
                        HttpStatus.NOT_FOUND);
            }

            Optional<OtpVerification> otpVerification = otpVerificationRepository
                    .findFirstByUserOrderByExpiresAtDesc(user);
            if (otpVerification.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse<>(false, "No OTP found for the given email", null, null),
                        HttpStatus.NOT_FOUND);
            }
            if (entity.getOtp() != null && entity.getOtp().equals(otpVerification.get().getOtp())
                    && LocalDateTime.now().isBefore(otpVerification.get().getExpiresAt())
                    && otpVerification.get().getIsVerified() == false) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(entity.getUser().getEmail());
                String token = jwtService.generateToken(userDetails);
                AuthResponse authResponse = new AuthResponse();
                authResponse.setToken(token);
                otpVerification.get().setIsVerified(true);
                otpVerificationRepository.save(otpVerification.get());

                return new ResponseEntity<>(new ApiResponse<>(true, "OTP verified successfully", authResponse, null),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "OTP is Invalid/Expired", null, null),
                    HttpStatus.FORBIDDEN);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(false, "Invalid token or expired token", null, null),
                    HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody UserDTO user) {
        try {
            if (!repository.existsByEmail(user.getEmail())) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Email not found", null, null),
                        HttpStatus.NOT_FOUND);
            }

            String token = UUID.randomUUID().toString();
            Context context = new Context();
            context.setVariable("name", user.getEmail());
            context.setVariable("subject", "Pathmates Security Code: Reset Password");
            context.setVariable("token", token);
            context.setVariable("email", user.getEmail());
            emailService.sentMail("", user.getEmail(), "Pathmates Security Code",
                    "EmailPasswordResetToken", context);

            PasswordResetToken resetToken = new PasswordResetToken();
            LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(5);
            resetToken.setExpiresAt(expirationTime);
            resetToken.setToken(token);
            resetToken.setUser(repository.findByEmail(user.getEmail()));

            passwordResetTokenRepository.save(resetToken);
            return new ResponseEntity<>(new ApiResponse<>(true, "Reset password link sent successfully", null, null),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error", null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register-user")
    public ResponseEntity<ApiResponse<String>> registerUser(@RequestBody UserDTO entity) {
        try {
            if (repository.existsByEmail(entity.getEmail())) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Email already exists", null, null),
                        HttpStatus.CONFLICT);
            }

            entity.setPassword(passwordEncoder.encode(entity.getPassword()));

            User user = mapper.mapToUser(entity);
            repository.save(user);

            return new ResponseEntity<>(new ApiResponse<>(true, "User registered successfully", null, null),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error", null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody PasswordResetTokenDTO entity) {
        try {
            User user = repository.findByEmail(entity.getUser().getEmail());
            if (user == null) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Email not found", null, null),
                        HttpStatus.NOT_FOUND);
            }

            Optional<PasswordResetToken> resetToken = passwordResetTokenRepository
                    .findFirstByUserOrderByExpiresAtDesc(user);
            if (resetToken.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse<>(false, "Invalid or expired token", null, null),
                        HttpStatus.FORBIDDEN);
            }

            if (entity.getPassword() != null && entity.getToken().equals(resetToken.get().getToken())
                    && LocalDateTime.now().isBefore(resetToken.get().getExpiresAt())
                    && resetToken.get().getIsVerified() == false) {
                user.setPassword(passwordEncoder.encode(entity.getPassword()));
                repository.save(user);
                resetToken.get().setIsVerified(true);
                passwordResetTokenRepository.save(resetToken.get());

                return new ResponseEntity<>(new ApiResponse<>(true, "Password reset successfully", null, null),
                        HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiResponse<>(false, "Token is expired", null, null),
                    HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ApiResponse<>(false, "Internal Server Error", null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
