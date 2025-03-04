package com.example.BookingService.repository;

import com.example.BookingService.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USERSERVICE")
public interface UserFeignClient {

    @GetMapping("/api/users/{id}")
    UserResponseDto getUserById(@PathVariable Integer id);
}
