package com.example.UserService.service;

import com.example.UserService.dto.*;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Integer id);
    void updateUser(Integer id, UserRequestDto userRequestDto);
    void deleteUser(Integer id);

}
