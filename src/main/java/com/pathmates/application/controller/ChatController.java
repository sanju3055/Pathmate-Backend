package com.pathmates.application.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.pathmates.application.dao.ChatMessageRepository;
import com.pathmates.application.dao.ContactRepository;
import com.pathmates.application.dao.TripRepository;
import com.pathmates.application.entities.ChatMessage;
import com.pathmates.application.entities.Contact;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private TripRepository tripRepository;

    @MessageMapping("/chat/{tripId}/sendMessage")
    public void sendMessage(@DestinationVariable String tripId, @Payload ChatMessage chatMessage) {
        Optional<Contact> optionalContact = contactRepository.findByTripIdAndPhoneNumber(tripId, chatMessage.getSender().getPhoneNumbers().get(0));
        if(optionalContact.isPresent()) {
            chatMessage.setSender(optionalContact.get());
            chatMessage.setTrip(tripRepository.findById(tripId).get());
            chatMessage.setTimestamp(LocalDateTime.now());
            System.out.println("📩 Received Message on Server - tripId: " + tripId + ", message: " + chatMessage);
            messagingTemplate.convertAndSend("/topic/trip/" + tripId, repository.save(chatMessage));
        }
    }
}
