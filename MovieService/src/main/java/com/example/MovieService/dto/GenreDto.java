package com.example.MovieService.dto;

import com.example.MovieService.entity.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenreDto {

    private int genreId;
    private String genreName;

    public static GenreDto mapToDto(Genre genre){
        return new GenreDto(genre.getGenreId(), genre.getGenreName());
    }

}
