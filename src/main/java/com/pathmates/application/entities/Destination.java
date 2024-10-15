package com.pathmates.application.entities;

import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "destination")
public class Destination extends Auditable {
    @Id
    @UuidGenerator
    private String destinationId;

    private String name;
    private double latitude;
    private double longitude;
    private String description;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToMany
    @JoinColumn(name = "destination_id")
    private Set<StayPoint> stayPoints;


}
