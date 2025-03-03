package com.example.AuthService.controller;

import com.example.AuthService.config.JwtService;
import com.example.AuthService.dto.LoginRequestDto;
import com.example.AuthService.dto.LoginResponseDto;
import com.example.AuthService.dto.UserRequestDto;
import com.example.AuthService.dto.UserResponseDto;
import com.example.AuthService.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserRequestDto userRequestDto) {
        try{
            userService.addUser(userRequestDto);
            return ResponseEntity.ok("User added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/token/{token}")
    public String verify(@PathVariable String token){
        if(jwtService.validateToken(token))
            return "Valid";
        return "Invalid";
    }

    @PostMapping("/login")
    public ResponseEntity<?> validate(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        try {
            LoginResponseDto result = userService.verify(loginRequestDto);
            return ResponseEntity.ok(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
