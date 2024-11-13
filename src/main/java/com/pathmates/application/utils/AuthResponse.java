package com.pathmates.application.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String userId;
    private String email;
    private String phoneNumber;
    private String name;
}
