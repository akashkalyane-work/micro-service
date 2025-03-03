package com.example.BookingService.controller;

import com.example.BookingService.dto.TheaterScreenDto;
import com.example.BookingService.service.TheaterScreenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/theaterScreens")
@RequiredArgsConstructor
public class TheaterScreenController {

    private final TheaterScreenService theaterScreenService;

    @GetMapping
    public List<TheaterScreenDto> getTheaterScreens() {
        return theaterScreenService.getTheaterScreens();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTheaterScreenById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(theaterScreenService.getTheaterScreenById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addTheaterScreen(@RequestBody TheaterScreenDto theaterScreenDto) {
        try{
            theaterScreenService.addTheaterScreen(theaterScreenDto);
            return ResponseEntity.ok("TheaterScreen added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateTheaterScreen(@PathVariable Integer id, @RequestBody TheaterScreenDto theaterScreenDto) {
        try{
            theaterScreenService.updateTheaterScreen(id, theaterScreenDto);
            return ResponseEntity.ok("TheaterScreen updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTheaterScreen(@PathVariable Integer id) {
        try{
            theaterScreenService.deleteTheaterScreen(id);
            return ResponseEntity.ok("TheaterScreen deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
