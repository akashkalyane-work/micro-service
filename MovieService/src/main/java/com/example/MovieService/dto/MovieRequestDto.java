package com.example.MovieService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequestDto {

    private String MovieName;
    private Integer GenreId;
    private String Grade;
    private Boolean IsAdult;
    private String ReleaseYear;
    private Integer DirectorId;
    private Integer MainActorMaleId;
    private Integer MainActorFemaleId;

}
