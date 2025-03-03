package com.example.BookingService.service.impl;

import com.example.BookingService.dto.TheaterDto;
import com.example.BookingService.entity.Theater;
import com.example.BookingService.repository.TheaterRepository;
import com.example.BookingService.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;


    @Override
    public List<TheaterDto> getTheaters() {
        return theaterRepository.findAll().stream()
                .map(TheaterDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TheaterDto getTheaterById(Integer id) {
        Theater theater = theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor Id not found"));
        return TheaterDto.mapToDto(theater);
    }

    @Override
    public void addTheater(TheaterDto theaterDto) {

    }

    @Override
    public void updateTheater(Integer id, TheaterDto theaterDto) {

    }

    @Override
    public void deleteTheater(Integer id) {

    }
}
