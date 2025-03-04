package com.example.BookingService.service.impl;

import com.example.BookingService.dto.BookingRequestDto;
import com.example.BookingService.dto.BookingResponseDto;
import com.example.BookingService.dto.MovieResponseDto;
import com.example.BookingService.dto.UserResponseDto;
import com.example.BookingService.entity.*;
import com.example.BookingService.repository.*;
import com.example.BookingService.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TheaterScreenRepository theaterScreenRepository;
    @Autowired
    private SlotRepository slotRepository;
    @Autowired
    private MovieFeignClient movieFeignClient;
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public List<BookingResponseDto> getBookings() {
        return bookingRepository.findAll().stream()
                .filter(x -> x.getDeletedBy() == null)
                .map(BookingResponseDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponseDto getBookingById(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Booking ID not found"));
        return BookingResponseDto.mapToDto(booking);
    }

    @Override
    public void addBooking(BookingRequestDto bookingRequestDto) {

        List<String> errors = new ArrayList<>();

        if(bookingRequestDto.getMovieId() == 0)
            errors.add("Movie Id required");
        else {
            MovieResponseDto movie = movieFeignClient.getMovieById(bookingRequestDto.getMovieId());
            if(movie == null)
                errors.add("Movie Id does not exist");
        }

        if(bookingRequestDto.getTheaterScreenId() == 0)
            errors.add("Theater screen Id required");
        else {
            TheaterScreen theaterScreen = theaterScreenRepository.findById(bookingRequestDto.getTheaterScreenId())
                    .orElse( null );
            if(theaterScreen == null)
                errors.add("Theater screen Id does not exist");
        }

        if(bookingRequestDto.getSlotId() == 0)
            errors.add("Slot Id required");
        else {
            Slot slot = slotRepository.findById(bookingRequestDto.getSlotId())
                    .orElse( null );
            if(slot == null)
                errors.add("Slot Id does not exist");
        }

        if(bookingRequestDto.getCreatedBy() == 0)
            errors.add("User Id required");
        else {
            UserResponseDto user = userFeignClient.getUserById(bookingRequestDto.getSlotId());
            if(user == null)
                errors.add("User Id does not exist");
        }

        if (!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

        Booking booking = new Booking();
        booking.setMovie(bookingRequestDto.getMovieId());
        booking.setTheaterScreen(new TheaterScreen(bookingRequestDto.getTheaterScreenId()));
        booking.setPrice(bookingRequestDto.getPrice());
        booking.setSlot(new Slot(bookingRequestDto.getSlotId()));
        booking.setNumberOfSlot(bookingRequestDto.getNumberOfSlot());
        booking.setCreatedBy(bookingRequestDto.getCreatedBy());
        booking.setCreateOn(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    @Override
    public void updateBooking(Integer id, BookingRequestDto bookingRequestDto) {
        Booking booking = bookingRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Booking ID not found"));
        booking.setTheaterScreen(new TheaterScreen(bookingRequestDto.getTheaterScreenId()));
        booking.setPrice(bookingRequestDto.getPrice());
        booking.setSlot(new Slot(bookingRequestDto.getSlotId()));
        booking.setNumberOfSlot(bookingRequestDto.getNumberOfSlot());
        booking.setChangedBy(1);
        booking.setChangedOn(LocalDateTime.now());
        bookingRepository.save(booking);
    }

    @Override
    public void deleteBooking(Integer id) {
        Booking booking = bookingRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Booking ID not found"));
//        booking.setDeletedBy(new User(1));  // fix this
        booking.setDeletedOn(LocalDateTime.now());
        bookingRepository.save(booking);
    }
}