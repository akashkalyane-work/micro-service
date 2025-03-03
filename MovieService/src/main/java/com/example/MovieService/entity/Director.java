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
public class Director {

    public Director(Integer directorId) {
        this.directorId = directorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer directorId;

    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false, length = 20)
    private String typeOfMovies;

    @Column(name = "createdBy")
    private Integer createdBy;

    private LocalDateTime createOn = LocalDateTime.now();

    private Integer changedBy;

    @Column
    private LocalDateTime changedOn;

    private Integer deletedBy;

    @Column
    private LocalDateTime deletedOn;
}
