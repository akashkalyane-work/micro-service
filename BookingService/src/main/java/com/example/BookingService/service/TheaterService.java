package com.example.BookingService.service;

import com.example.BookingService.dto.TheaterDto;

import java.util.List;

public interface TheaterService {
    List<TheaterDto> getTheaters();
    TheaterDto getTheaterById(Integer id);
    void addTheater(TheaterDto theaterDto);
    void updateTheater(Integer id, TheaterDto theaterDto);
    void deleteTheater(Integer id);
}
