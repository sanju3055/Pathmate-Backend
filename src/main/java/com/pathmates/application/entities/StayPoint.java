package com.pathmates.application.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stay_point")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class StayPoint extends Auditable {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String stayPointId;

    private String name;
    private double latitude;
    private double longitude;
    private String description;

    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "destination_id")
    private Destination destination;

}
