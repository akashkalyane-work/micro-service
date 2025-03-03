package com.example.MovieService.service;

import com.example.MovieService.dto.MovieRequestDto;
import com.example.MovieService.dto.MovieResponseDto;

import java.util.List;

public interface MovieService {
    List<MovieResponseDto> getMovies();
    MovieResponseDto getMovieById(Integer id);
    void addMovie(MovieRequestDto movieRequestDto);
    void updateMovie(Integer id, MovieRequestDto movieRequestDto);
    void deleteMovie(Integer id);
}
