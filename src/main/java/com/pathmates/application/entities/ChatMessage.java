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

@Entity
@Table(name = "chat_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ChatMessage extends Auditable {
    @Id
    @UuidGenerator
    @EqualsAndHashCode.Include
    private String chatMessageId;
    private String content;
    private Double latitude;
    private Double longitude;
    private LocalDateTime timestamp;
    private String location;

    @ManyToOne()
    @JoinColumn(name = "sender_id", nullable = false)
    private Contact sender;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "trip_id", nullable = false)
    private Trip trip;

}
