package com.pathmates.application.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.pathmates.application.dao.ChatMessageRepository;
import com.pathmates.application.entities.ChatMessage;

@Controller
public class ChatController {

    @Autowired
    private ChatMessageRepository repository;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/trip/{tripId}")
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        chatMessage.setTimestamp(LocalDateTime.now());
        repository.save(chatMessage);
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/trip/{tripId}")
    public ChatMessage addUser(ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender().getDisplayName());
        return chatMessage;
    }
}
