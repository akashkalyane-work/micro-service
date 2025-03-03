package com.example.BookingService.controller;

import com.example.BookingService.dto.TheaterDto;
import com.example.BookingService.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;

    @GetMapping
    public List<TheaterDto> getTheaters() {
        return theaterService.getTheaters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTheaterById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(theaterService.getTheaterById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addTheater(@RequestBody TheaterDto theaterDto) {
        try{
            theaterService.addTheater(theaterDto);
            return ResponseEntity.ok("Theater added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTheater(@PathVariable Integer id, @RequestBody TheaterDto theaterDto) {
        try{
            theaterService.updateTheater(id, theaterDto);
            return ResponseEntity.ok("Theater updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheater(@PathVariable Integer id) {
        try{
            theaterService.deleteTheater(id);
            return ResponseEntity.ok("Theater deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
