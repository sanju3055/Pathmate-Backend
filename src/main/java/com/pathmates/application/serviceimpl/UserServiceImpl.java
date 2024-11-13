package com.pathmates.application.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pathmates.application.dao.UserRepository;
import com.pathmates.application.dto.UserDTO;
import com.pathmates.application.entities.User;
import com.pathmates.application.mapper.UserMapper;
import com.pathmates.application.service.UserService;
import com.pathmates.application.utils.ApiResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    @Override
    public ApiResponse<UserDTO> createUser(UserDTO userDTO) {
        return new ApiResponse<>(true, "", repository.save(mapper.mapToUser(userDTO)), null);
    }

    @Override
    public ApiResponse<UserDTO> getUserById(String userId) {
        return new ApiResponse<>(true, "", repository.findById(userId).get(), null);
    }

    @Override
    public ApiResponse<UserDTO> updateUser(String userId,UserDTO userDTO) {
        Optional<User> user = repository.findById(userId);
        if (user.isPresent()) {
            user.get().setEmail(userDTO.getEmail());
            user.get().setPhoneNumber(userDTO.getPhoneNumber());
            user.get().setName(userDTO.getName());
            return new ApiResponse<>(true, "", repository.save(user.get()), null);
        }
        return new ApiResponse<>(true, "", "User is not found", null);
    }

    @Override
    public void deleteUser(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

    @Override
    public ApiResponse<UserDTO> loginUser(String email, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

    @Override
    public boolean isUserEmailAndPhoneNumberTaken(String email, String phoneNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserEmailAndPhoneNumberTaken'");
    }

}
