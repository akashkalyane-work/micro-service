package com.example.BookingService.service.impl;

import com.example.BookingService.dto.TheaterScreenDto;
import com.example.BookingService.entity.TheaterScreen;
import com.example.BookingService.repository.TheaterScreenRepository;
import com.example.BookingService.service.TheaterScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterScreenServiceImpl implements TheaterScreenService {

    private final TheaterScreenRepository theaterScreenRepository;

    @Override
    public List<TheaterScreenDto> getTheaterScreens() {
        return theaterScreenRepository.findAll().stream()
                .map(TheaterScreenDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TheaterScreenDto getTheaterScreenById(Integer id) {
        TheaterScreen theaterScreen = theaterScreenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater Screen Id not found"));
        return TheaterScreenDto.mapToDto(theaterScreen);
    }

    @Override
    public void addTheaterScreen(TheaterScreenDto theaterScreenDto) {

    }

    @Override
    public void updateTheaterScreen(Integer id, TheaterScreenDto theaterScreenDto) {

    }

    @Override
    public void deleteTheaterScreen(Integer id) {

    }
}
