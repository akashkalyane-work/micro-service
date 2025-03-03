package com.example.MovieService.dto;

import com.example.MovieService.entity.Actor;
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

    public static ActorDto mapToDto(Actor actor){
        return new ActorDto(
                actor.getActorId(),
                actor.getName(),
                actor.getAge(),
                actor.getHasAward(),
                actor.getNoOfMoviesWorkedOn()
        );
    }

}
