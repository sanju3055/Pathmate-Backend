package com.pathmates.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pathmates.application.dao.ChatMessageRepository;
import com.pathmates.application.dto.ChatMessageDTO;
import com.pathmates.application.mapper.ChatMessageMapper;
import com.pathmates.application.serviceimpl.ChatMessageServiceImpl;
import com.pathmates.application.utils.ApiResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/chat-message")
public class ChatMessageController {

    @Autowired
    private ChatMessageServiceImpl serviceImpl;
    @Autowired
    private ChatMessageRepository repository;
    @Autowired
    private ChatMessageMapper mapper;

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ChatMessageDTO>> updateChatMessage(@PathVariable String id,
            @RequestBody ChatMessageDTO entity) {
        return new ResponseEntity<ApiResponse<ChatMessageDTO>>(
                new ApiResponse<>(true, "", serviceImpl.updateChatMessage(id, entity), null), HttpStatus.OK);
    }

    @DeleteMapping("/{chatMessageId}")
    public ResponseEntity<ApiResponse<String>> deleteChatMessage(@PathVariable String chatMessageId) {
        return new ResponseEntity<>(serviceImpl.deleteChatMessage(chatMessageId), HttpStatus.OK);
    }

}
