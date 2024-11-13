package com.pathmates.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import com.pathmates.application.dto.UserDTO;
import com.pathmates.application.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO mapToUserDTO(User user);
    User mapToUser(UserDTO userDTO);
    List<UserDTO> mapToUserDTOList(List<User> users);
    List<User> mapToUserList(List<UserDTO> userDTOs);
}
