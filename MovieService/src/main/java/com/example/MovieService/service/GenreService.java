package com.example.MovieService.service;

import com.example.MovieService.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getGenres();
    GenreDto getGenreById(Integer id);
    void addGenre(GenreDto genreDto);
    void updateGenre(Integer id, GenreDto genreDto);
    void deleteGenre(Integer id);
}
