package com.example.AuthService.service.impl;

import com.example.AuthService.config.JwtService;
import com.example.AuthService.dto.*;
import com.example.AuthService.entity.*;
import com.example.AuthService.repository.UserRepository;
import com.example.AuthService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;

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
    public void addUser(UserRequestDto userRequestDto) {

        List<String> errors = new ArrayList<>();

//        String salt = BCrypt.gensalt();
//        String hashedPassword = BCrypt.hashpw(userRequestDto.getPassword(), salt);

        if (userRequestDto.getUsername() == null || userRequestDto.getUsername().trim().isEmpty())
            errors.add("Username is required");

        if ((userRequestDto.getUsername() != null && !userRequestDto.getUsername().trim().isEmpty()) && userRequestDto.getUsername().trim().length() < 3)
            errors.add("Username must be more than or equals to 3 charters");
        else {
            User userIsExist = userRepository.findByUserName(userRequestDto.getUsername());
            if(userIsExist != null)
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
            if(emailIsExist != null)
                errors.add("Email already taken");
        }

        if (!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

        User user = new User(null,
                userRequestDto.getEmail(),
                userRequestDto.getUsername(),
//                userRequestDto.getPassword(),
                encoder.encode(userRequestDto.getPassword()),
                null,
                null,
                LocalDateTime.now()
        );
        userRepository.save(user);
    }

    @Override
    public int validate(LoginRequestDto loginRequestDto) {

        List<String> errors = new ArrayList<>();
        User user = null;

        if (loginRequestDto.getEmail() == null || loginRequestDto.getEmail().trim().isEmpty())
            errors.add("Email is required");
        else{
            if (!ValidateEmail.matcher(loginRequestDto.getEmail()).matches())
                errors.add("Invalid Email");
            else{
                user = userRepository.findByEmail(loginRequestDto.getEmail());
                if(user == null)
                    errors.add("Email not found");
            }
        }

        if (loginRequestDto.getPassword() == null || loginRequestDto.getPassword().trim().isEmpty())
            errors.add("Password is required");
        else {
            if(loginRequestDto.getPassword().trim().length() < 8)
                errors.add("Password must be more than or equals to 8 charters");
        }

        if(!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

//        if(BCrypt.checkpw(loginRequestDto.getPassword(), user.getHashPassword()))
//            return user.getUserId();

        if(encoder.matches(loginRequestDto.getPassword(), user.getPassword()))
            return user.getUserId();

        return 0;
    }

    @Override
    public LoginResponseDto verify(LoginRequestDto loginRequestDto){

        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail()
                        , loginRequestDto.getPassword()
                ));
        if(authentication.isAuthenticated()){
            User user = userRepository.findByEmail(loginRequestDto.getEmail());
            return new LoginResponseDto(jwtService.generateToken(loginRequestDto.getEmail(), user.getRole().getRoleName()), user.getUserId());
        }

        return null;
    }

    @Override
    public String generateToken(String email) {
        return jwtService.generateToken(email, "user");
    }

}
