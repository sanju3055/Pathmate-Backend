package com.pathmates.application.dto;

import java.util.Set;

import com.pathmates.application.entities.StayPoint;
import com.pathmates.application.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DestinationDTO {
    private String destinationId;

    private String name;
    private double latitude;
    private double longitude;
    private String description;

    private Trip trip;
    private Set<StayPoint> stayPoints;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
