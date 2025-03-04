package com.example.BookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActorDto {

    private Integer actorId;
    private String name;
    private Integer age;
    private Boolean hasAward;
    private Integer noOfMoviesWorkedOn;

}
