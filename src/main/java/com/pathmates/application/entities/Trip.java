package com.pathmates.application.entities;

import java.time.LocalDateTime;
import java.util.Set;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
@Table(name = "trip")
public class Trip extends Auditable {

    @Id
    @UuidGenerator
    private String tripId;
    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @OneToMany()
    @JoinColumn(name = "trip")
    private Set<ChatMessage> chatMessages;

    @OneToMany()
    @JoinColumn(name = "trip_id")
    private Set<Destination> destinations;

    @OneToMany(mappedBy = "trip")
    private Set<Contact> contacts;

    @ManyToMany
    @JoinTable(name = "trip_user", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users;

}
