package com.pathmates.application.dto;

import java.time.LocalDateTime;

import com.pathmates.application.entities.Contact;
import com.pathmates.application.entities.Trip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChatMessageDTO {
    private String chatMessageId;
    private Contact sender;
    private String content;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
    private String location;
    private Trip trip;
    private String createdBy;

    private Instant createdAt;

    private String lastModifiedBy;

    private Instant lastModifiedAt;

    private String deletedBy;

    private Instant deletedAt;

}
