package com.pathmates.application.utils;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class InstantMessageAddress {
    
    private String username;
    
    private String service;

    // Getters and Setters
}
