package com.example.MovieService.dto;

import com.example.MovieService.entity.Director;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DirectorDto {

    private Integer directorId;
    private String name;
    private Integer age;
    private String typeOfMovies;

    public static DirectorDto mapToDto(Director director){
        return new DirectorDto(
                director.getDirectorId(),
                director.getName(),
                director.getAge(),
                director.getTypeOfMovies()
        );
    }

}
