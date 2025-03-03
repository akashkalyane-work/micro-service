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
public class Genre {

    public Genre(Integer genreId) {
        this.genreId = genreId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer genreId;

    @Column(nullable = false, length = 40)
    private String genreName;

    @Column(name = "createdBy", nullable = false)
    private Integer createdBy;

    @Column(name = "createdOn", nullable = false)
    private LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "changedBy")
    private Integer changedBy;

    @Column
    private LocalDateTime changedOn;

    @Column(name = "deletedBy")
    private Integer deletedBy;

    @Column
    private LocalDateTime deletedOn;

}
