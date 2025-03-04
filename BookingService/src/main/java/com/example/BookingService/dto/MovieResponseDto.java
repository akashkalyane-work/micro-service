package com.example.BookingService.dto;

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
}
