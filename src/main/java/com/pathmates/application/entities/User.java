package com.pathmates.application.entities;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User extends Auditable {

    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String userId;
    private String email;
    private String password;
    private String phoneNumber;
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Trip> trips;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OtpVerification> otpVerifications;
    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PasswordResetToken> passwordResetTokens;
}
