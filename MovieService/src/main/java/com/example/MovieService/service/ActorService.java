package com.example.MovieService.service;

import com.example.MovieService.dto.ActorDto;

import java.util.List;

public interface ActorService {
    List<ActorDto> getActors();
    ActorDto getActorById(Integer id);
    void addActor(ActorDto actorDto);
    void updateActor(Integer id, ActorDto actorDto);
    void deleteActor(Integer id);
}
