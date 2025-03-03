package com.example.BookingService.service;

import com.example.BookingService.dto.TheaterScreenDto;

import java.util.List;

public interface TheaterScreenService {
    List<TheaterScreenDto> getTheaterScreens();
    TheaterScreenDto getTheaterScreenById(Integer id);
    void addTheaterScreen(TheaterScreenDto theaterScreenDto);
    void updateTheaterScreen(Integer id, TheaterScreenDto theaterScreenDto);
    void deleteTheaterScreen(Integer id);
}
