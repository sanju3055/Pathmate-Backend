package com.pathmates.application.dto;

import java.util.Set;

import com.pathmates.application.entities.ChatMessage;
import com.pathmates.application.entities.Contact;
import com.pathmates.application.entities.Destination;
import com.pathmates.application.entities.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TripDTO {
    private String tripId;
    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Set<ChatMessage> chatMessages;
    private Set<Destination> destinations;
    private Set<Contact> contacts;
    private Set<User> users;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;
}
