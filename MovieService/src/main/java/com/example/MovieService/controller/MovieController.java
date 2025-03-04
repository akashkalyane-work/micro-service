package com.example.MovieService.controller;

import com.example.MovieService.dto.MovieRequestDto;
import com.example.MovieService.dto.MovieResponseDto;
import com.example.MovieService.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieResponseDto> getUsers() {
        return movieService.getMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDto> getMovieById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(movieService.getMovieById(id));
        } catch (Exception e){
//            return ResponseEntity.badRequest().body(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody MovieRequestDto movieRequestDto) {
        try{
            movieService.addMovie(movieRequestDto);
            return ResponseEntity.ok("Movie added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable Integer id, @RequestBody MovieRequestDto movieRequestDto) {
        try{
            movieService.updateMovie(id, movieRequestDto);
            return ResponseEntity.ok("Movie updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Integer id) {
        try{
            movieService.deleteMovie(id);
            return ResponseEntity.ok("Movie deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
