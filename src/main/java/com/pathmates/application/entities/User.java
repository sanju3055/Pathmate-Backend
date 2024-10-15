package com.pathmates.application.entities;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User extends Auditable {

    @Id
    @UuidGenerator
    private String userId;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;

    @ManyToMany(mappedBy = "users")
    private Set<Trip> trips;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OtpVerification> otpVerifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordResetToken> passwordResetTokens;
}
