package com.example.MovieService.service.impl;

import com.example.MovieService.dto.MovieRequestDto;
import com.example.MovieService.dto.MovieResponseDto;
import com.example.MovieService.entity.*;
import com.example.MovieService.repository.ActorRepository;
import com.example.MovieService.repository.DirectorRepository;
import com.example.MovieService.repository.GenreRepository;
import com.example.MovieService.repository.MovieRepository;
import com.example.MovieService.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Override
    public List<MovieResponseDto> getMovies() {
        return movieRepository.findAll().stream()
                .filter(x -> x.getDeletedBy() == null)
                .map(MovieResponseDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public MovieResponseDto getMovieById(Integer id) {
        Movie movie = movieRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Movie ID not found"));
        return MovieResponseDto.mapToDto(movie);
    }

    @Override
    public void addMovie(MovieRequestDto movieRequestDto) {

        List<String> errors = new ArrayList<>();

        if(movieRequestDto.getMovieName() == null || movieRequestDto.getMovieName().trim().isEmpty())
            errors.add("Movie name is required");
        else{
            if(movieRequestDto.getMovieName().trim().length() < 3)
                errors.add("Movie name should be more than or equals to 3 charters");
            else {
                Movie isNameExist = movieRepository.findByMovieName(movieRequestDto.getMovieName());
                if(isNameExist != null)
                    errors.add("Movie name already taken");
            }
        }

        if(movieRequestDto.getReleaseYear() == null)
            errors.add("Movie release year is required");
        else
            if(!(movieRequestDto.getReleaseYear().trim().length() == 4))
                errors.add("Release year is not valid");

        if(movieRequestDto.getIsAdult() == null)
            errors.add("Is adult required");

        if(movieRequestDto.getGrade() == null)
            errors.add("Movie grade is required");

        if(movieRequestDto.getGenreId() == null)
            errors.add("Genre Id is required");
        else{
            Genre genre = genreRepository.findById(movieRequestDto.getGenreId()).orElse(null);
            if(genre == null)
                errors.add("Genre Id not exist");
        }

        if(movieRequestDto.getDirectorId() == null)
            errors.add("Director Id is required");
        else {
            Director director = directorRepository.findById(Long.valueOf(movieRequestDto.getDirectorId())).orElse(null);
            if(director == null)
                errors.add("Genre Id not found");
        }

        if(movieRequestDto.getMainActorMaleId() == null)
            errors.add("Male actor Id is required");
        else {
            Actor actor = actorRepository.findById(movieRequestDto.getMainActorMaleId()).orElse(null);
            if(actor == null)
                errors.add("Male actor Id not found");
        }

        if(movieRequestDto.getMainActorFemaleId() == null)
            errors.add("Female actor Id is required");
        else{
            Actor actor = actorRepository.findById(movieRequestDto.getMainActorFemaleId()).orElse(null);
            if(actor == null)
                errors.add("Female actor Id not found");
        }

        if(!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

        Movie movie = new Movie();
        movie.setMovieName(movieRequestDto.getMovieName());
        movie.setReleaseYear(movieRequestDto.getReleaseYear());
        movie.setGrade(movieRequestDto.getGrade());
        movie.setIsAdult(movieRequestDto.getIsAdult());
        movie.setGenre(new Genre(movieRequestDto.getGenreId()));
        movie.setDirector(new Director(movieRequestDto.getDirectorId()));
        movie.setMainActorMale(new Actor(movieRequestDto.getMainActorMaleId()));
        movie.setMainActorFemale(new Actor(movieRequestDto.getMainActorFemaleId()));
        movie.setCreatedBy(1); // fix this
    }

    @Override
    public void updateMovie(Integer id, MovieRequestDto movieRequestDto) {
        Movie movie = movieRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Movie ID not found"));

        List<String> errors = new ArrayList<>();

        if(movieRequestDto.getMovieName() == null || movieRequestDto.getMovieName().trim().isEmpty())
            errors.add("Movie name is required");
        else{
            if(movieRequestDto.getMovieName().trim().length() < 3)
                errors.add("Movie name should be more than or equals to 3 charters");
            else {
                Movie isNameExist = movieRepository.findByMovieName(movieRequestDto.getMovieName());
                if(isNameExist != null && isNameExist.getMovieId() != id)
                    errors.add("Movie name already taken");
            }
        }

        if(movieRequestDto.getReleaseYear() == null)
            errors.add("Movie release year is required");
        else
        if(!(movieRequestDto.getReleaseYear().trim().length() == 4))
            errors.add("Release year is not valid");

        if(movieRequestDto.getIsAdult() == null)
            errors.add("Is adult required");

        if(movieRequestDto.getGrade() == null)
            errors.add("Movie grade is required");

        if(movieRequestDto.getGenreId() == null)
            errors.add("Genre Id is required");
        else{
            Genre genre = genreRepository.findById(movieRequestDto.getGenreId()).orElse(null);
            if(genre == null)
                errors.add("Genre Id not exist");
        }

        if(movieRequestDto.getDirectorId() == null)
            errors.add("Director Id is required");
        else {
            Director director = directorRepository.findById(Long.valueOf(movieRequestDto.getDirectorId())).orElse(null);
            if(director == null)
                errors.add("Director Id not found");
        }

        if(movieRequestDto.getMainActorMaleId() == null)
            errors.add("Male actor Id is required");
        else {
            Actor actor = actorRepository.findById(movieRequestDto.getMainActorMaleId()).orElse(null);
            if(actor == null)
                errors.add("Male actor Id not found");
        }

        if(movieRequestDto.getMainActorFemaleId() == null)
            errors.add("Female actor Id is required");
        else{
            Actor actor = actorRepository.findById(movieRequestDto.getMainActorFemaleId()).orElse(null);
            if(actor == null)
                errors.add("Female actor Id not found");
        }

        if(!errors.isEmpty())
            throw new RuntimeException(String.join("\n", errors));

        movie.setMovieName(movieRequestDto.getMovieName());
        movie.setReleaseYear(movieRequestDto.getReleaseYear());
        movie.setGrade(movieRequestDto.getGrade());
        movie.setIsAdult(movieRequestDto.getIsAdult());
        movie.setGenre(new Genre(movieRequestDto.getGenreId()));
        movie.setDirector(new Director(movieRequestDto.getDirectorId()));
        movie.setMainActorMale(new Actor(movieRequestDto.getMainActorMaleId()));
        movie.setMainActorFemale(new Actor(movieRequestDto.getMainActorFemaleId()));

        movieRepository.save(movie);
    }

    @Override
    public void deleteMovie(Integer id) {
        Movie movie = movieRepository.findById(id)
                .filter(x -> x.getDeletedBy() == null)
                .orElseThrow(() -> new RuntimeException("Movie ID not found"));
        movie.setDeletedBy(1); // fix this
        movie.setDeletedOn(LocalDateTime.now());
        movieRepository.save(movie);
    }
}
