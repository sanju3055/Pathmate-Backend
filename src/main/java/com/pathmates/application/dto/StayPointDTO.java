package com.pathmates.application.dto;

import java.time.LocalDateTime;

import com.pathmates.application.entities.Destination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class StayPointDTO {
private String stayPointId;

    private String name;
    private double latitude;
    private double longitude;
    private String description;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    private Destination destination;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
