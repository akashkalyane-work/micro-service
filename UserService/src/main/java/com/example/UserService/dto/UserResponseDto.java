package com.example.UserService.dto;

import com.example.UserService.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Integer userId;
    private String userName;
    private String email;

    public static UserResponseDto mapToDto(User user) {
        return new UserResponseDto(
                user.getUserId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
