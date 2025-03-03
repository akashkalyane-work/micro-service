package com.example.MovieService.controller;

import com.example.MovieService.dto.GenreDto;
import com.example.MovieService.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/genres")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping
    public List<GenreDto> getGenres() {
        return genreService.getGenres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Integer id) {
        try{
            return ResponseEntity.ok(genreService.getGenreById(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> addGenre(@RequestBody GenreDto genreDto) {
        try{
            genreService.addGenre(genreDto);
            return ResponseEntity.ok("Genre added successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateGenre(@PathVariable Integer id, @RequestBody GenreDto genreDto) {
        try{
            genreService.updateGenre(id, genreDto);
            return ResponseEntity.ok("Genre updated successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGenre(@PathVariable Integer id) {
        try{
            genreService.deleteGenre(id);
            return ResponseEntity.ok("Genre deleted successfully");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
