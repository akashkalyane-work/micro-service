package com.example.AuthService.service;

import com.example.AuthService.dto.*;

import java.util.List;

public interface UserService {

    List<UserResponseDto> getUsers();
    UserResponseDto getUserById(Integer id);
    void addUser(UserRequestDto userRequestDto);
    int validate(LoginRequestDto loginRequestDto);
    LoginResponseDto verify(LoginRequestDto loginRequestDto);

    String generateToken(String email);

}
