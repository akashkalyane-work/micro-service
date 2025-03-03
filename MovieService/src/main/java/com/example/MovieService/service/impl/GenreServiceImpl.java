package com.example.MovieService.service.impl;

import com.example.MovieService.dto.GenreDto;
import com.example.MovieService.entity.Genre;
import com.example.MovieService.repository.GenreRepository;
import com.example.MovieService.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreDto> getGenres() {
        return genreRepository.findAll().stream()
                .map(GenreDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GenreDto getGenreById(Integer id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actor Id not found"));
        return GenreDto.mapToDto(genre);
    }

    @Override
    public void addGenre(GenreDto genreDto) {

    }

    @Override
    public void updateGenre(Integer id, GenreDto genreDto) {

    }

    @Override
    public void deleteGenre(Integer id) {

    }
}
