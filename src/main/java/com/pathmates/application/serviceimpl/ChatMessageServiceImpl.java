package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.ChatMessageRepository;
import com.pathmates.application.dto.ChatMessageDTO;
import com.pathmates.application.entities.ChatMessage;
import com.pathmates.application.mapper.ChatMessageMapper;
import com.pathmates.application.service.ChatMessageService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository repository;

    @Autowired
    private ChatMessageMapper mapper;

    @Override
    public ApiResponse<ChatMessageDTO> createChatMessage(ChatMessageDTO chatMessageDTO) {
        return new ApiResponse<>(true, "",
                mapper.mapToChatMessageDTO(repository.save(mapper.mapToChatMessage(chatMessageDTO))), null);
    }

    @Override
    public ApiResponse<ChatMessageDTO> getChatMessageById(String chatMessageId) {
        return new ApiResponse<>(true, "", mapper.mapToChatMessageDTO(repository.findById(chatMessageId).get()), null);
    }

    @Override
    public ApiResponse<ChatMessageDTO> updateChatMessage(String chatMessageId, ChatMessageDTO chatMessageDTO) {
        Optional<ChatMessage> chatMessage = repository.findById(chatMessageId);
        if (chatMessage.isPresent()) {
            chatMessage.get().setContent(chatMessageDTO.getContent());
            return new ApiResponse<>(true, "", mapper.mapToChatMessageDTO(repository.save(chatMessage.get())), null);
        }
        return new ApiResponse<>(true, "", "Chat message is not found", null);
    }

    @Override
    public ApiResponse<String> deleteChatMessage(String chatMessageId) {
        Optional<ChatMessage> chatMessage = repository.findById(chatMessageId);
        if (chatMessage.isPresent()) {
            repository.delete(chatMessage.get());
            return new ApiResponse<>(true, "", "Chat message deleted successfully", null);
        } else {
            throw new IllegalArgumentException("Chat message not found");
        }
    }

}
