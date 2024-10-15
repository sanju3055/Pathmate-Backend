package com.pathmates.application.service;

import com.pathmates.application.dto.UserDTO;
import com.pathmates.application.utils.ApiResponse;

public interface UserService {
    ApiResponse<UserDTO> createUser(UserDTO userDTO);
    ApiResponse<UserDTO> getUserById(String userId);
    ApiResponse<UserDTO> updateUser(String userId,UserDTO userDTO);
    void deleteUser(String userId);
    ApiResponse<UserDTO> loginUser(String email, String password);
    boolean isUserEmailAndPhoneNumberTaken(String email, String phoneNumber);
}
