package com.pathmates.application.service;

import com.pathmates.application.dto.ChatMessageDTO;
import com.pathmates.application.utils.ApiResponse;

public interface ChatMessageService {
    ApiResponse<ChatMessageDTO> createChatMessage(ChatMessageDTO chatMessageDTO);
    ApiResponse<ChatMessageDTO> getChatMessageById(String chatMessageId);
    ApiResponse<ChatMessageDTO> updateChatMessage(String chatMessageId, ChatMessageDTO chatMessageDTO);
    void deleteChatMessage(String chatMessageId);
}
