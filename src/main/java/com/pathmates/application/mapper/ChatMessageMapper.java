package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.ChatMessageDTO;
import com.pathmates.application.entities.ChatMessage;


@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageDTO mapToChatMessageDTO(ChatMessage contact);
    ChatMessage mapToChatMessage(ChatMessageDTO contactDTO);
    List<ChatMessageDTO>  mapToChatMessageDTOList(List<ChatMessage> contacts);
    List<ChatMessage> mapToChatMessageList(List<ChatMessageDTO> contactDTOs);
}
