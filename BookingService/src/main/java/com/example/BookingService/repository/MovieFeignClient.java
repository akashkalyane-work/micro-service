package com.example.BookingService.repository;

import com.example.BookingService.dto.MovieResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MOVIESERVICE")
public interface MovieFeignClient {

    @GetMapping("/api/movies/{id}")
    MovieResponseDto getMovieById(@PathVariable Integer id);
}
