package com.example.UserService.dto;

import com.example.UserService.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    private String username;
    private String email;
    private String password;

    public static UserRequestDto mapToDto(User user) {
        return new UserRequestDto(
                user.getUserName(),
                user.getEmail(),
                user.getPassword()
        );
    }

}
