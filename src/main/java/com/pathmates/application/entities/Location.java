package com.pathmates.application.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "location")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Location extends Auditable {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String locationId;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
}
