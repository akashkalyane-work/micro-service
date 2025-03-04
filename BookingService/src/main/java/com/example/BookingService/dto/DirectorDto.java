package com.example.BookingService.dto;

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

}
