package com.example.MovieService.repository;

import com.example.MovieService.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByMovieName(String movieName);
}
