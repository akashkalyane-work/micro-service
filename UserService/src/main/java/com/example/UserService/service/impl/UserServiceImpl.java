package com.example.UserService.service.impl;

import com.example.UserService.dto.*;
import com.example.UserService.entity.*;
import com.example.UserService.repository.UserRepository;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public static final Pattern ValidateEmail =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    public List<UserResponseDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found"));
        return UserResponseDto.mapToDto(user);
    }

    @Override
    public void updateUser(Integer id, UserRequestDto userRequestDto) {

        List<String> errors = new ArrayList<>();

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found"));

        if (userRequestDto.getUsername() == null || userRequestDto.getUsername().trim().isEmpty())
            errors.add("Username is required");

        if ((userRequestDto.getUsername() != null && !userRequestDto.getUsername().trim().isEmpty()) && userRequestDto.getUsername().trim().length() < 3)
            errors.add("Username must be more than or equals to 3 charters");
        else {
            User userIsExist = userRepository.findByUserName(userRequestDto.getUsername());
            if(userIsExist != null && !Objects.equals(userIsExist.getUserId(), id))
                errors.add("Username is already taken");
        }

        if (userRequestDto.getPassword() == null || userRequestDto.getPassword().trim().isEmpty())
            errors.add("Password is required");

        if ((userRequestDto.getPassword() != null && !userRequestDto.getPassword().trim().isEmpty()) && userRequestDto.getPassword().trim().length() < 8)
            errors.add("Password must be more than or equals to 8 charters");

        if (userRequestDto.getEmail() == null || userRequestDto.getEmail().trim().isEmpty())
            errors.add("Email is required");

        if ((userRequestDto.getEmail() != null && !userRequestDto.getEmail().trim().isEmpty()) && !ValidateEmail.matcher(userRequestDto.getEmail()).matches())
            errors.add("Invalid Email");
        else{
            User emailIsExist = userRepository.findByEmail(userRequestDto.getEmail());
            if(emailIsExist != null && !Objects.equals(emailIsExist.getUserId(), id))
                    errors.add("Email already taken");
        }

        if (!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

        user.setUserName(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(encoder.encode(userRequestDto.getPassword()));

        userRepository.save(user);

    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User ID not found"));
        userRepository.delete(user);
    }



}
