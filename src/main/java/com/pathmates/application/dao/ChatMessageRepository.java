package com.pathmates.application.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pathmates.application.entities.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

}
