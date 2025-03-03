package com.example.MovieService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    public Movie(Integer movieId) {
        this.movieId = movieId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false, length = 50)
    private String movieName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genreId", nullable = false)
    private Genre genre;

    @Column(nullable = false, length = 5)
    private String grade;

    @Column(nullable = false)
    private Boolean isAdult;

    @Column(nullable = false, length = 10)
    private String releaseYear;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "directorId", nullable = false)
    private Director director;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mainActorMale", nullable = false)
    private Actor mainActorMale;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "mainActorFemale", nullable = false)
    private Actor mainActorFemale;

    @Column(name = "createdBy", nullable = false)
    private Integer createdBy;

    @Column(nullable = false)
    private LocalDateTime createOn = LocalDateTime.now();

    @Column(name = "changedBy")
    private Integer changedBy;

    @Column
    private LocalDateTime changedOn;

    @Column(name = "deletedBy")
    private Integer deletedBy;

    @Column
    private LocalDateTime deletedOn;
}
