package com.example.BookingService.controller;

import com.example.BookingService.dto.BookingRequestDto;
import com.example.BookingService.dto.BookingResponseDto;
import com.example.BookingService.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @GetMapping
    public List<BookingResponseDto> getBookings() {
        return bookingService.getBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookingById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(bookingService.getBookingById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        try{
            bookingService.addBooking(bookingRequestDto);
            return ResponseEntity.ok("Booking added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Integer id, @RequestBody BookingRequestDto bookingRequestDto) {
        try{
            bookingService.updateBooking(id, bookingRequestDto);
            return ResponseEntity.ok("Booking updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer id) {
        try{
            bookingService.deleteBooking(id);
            return ResponseEntity.ok("Booking deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
