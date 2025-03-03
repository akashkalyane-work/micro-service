package com.example.BookingService.service;

import com.example.BookingService.dto.BookingRequestDto;
import com.example.BookingService.dto.BookingResponseDto;

import java.util.List;

public interface BookingService {
    List<BookingResponseDto> getBookings();
    BookingResponseDto getBookingById(Integer id);
    void addBooking(BookingRequestDto bookingRequestDto);
    void updateBooking(Integer id, BookingRequestDto bookingRequestDto);
    void deleteBooking(Integer id);
}