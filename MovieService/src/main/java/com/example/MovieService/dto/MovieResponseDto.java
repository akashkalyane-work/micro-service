package com.example.MovieService.dto;

import com.example.MovieService.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponseDto {

    private Integer movieId;
    private String movieName;
    private String grade;
    private Boolean isAdult;
    private String releaseYear;
    private GenreDto genre;
    private DirectorDto director;
    private ActorDto mainActorMale;
    private ActorDto mainActorFemale;

    public static MovieResponseDto mapToDto(Movie movie) {
        return new MovieResponseDto(
                movie.getMovieId(),
                movie.getMovieName(),
                movie.getGrade(),
                movie.getIsAdult(),
                movie.getReleaseYear(),
                GenreDto.mapToDto(movie.getGenre()),
                DirectorDto.mapToDto(movie.getDirector()),
                ActorDto.mapToDto(movie.getMainActorMale()),
                ActorDto.mapToDto(movie.getMainActorFemale())
        );
    }
}
