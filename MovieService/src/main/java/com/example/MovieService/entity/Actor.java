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
public class Actor {

    public Actor(Integer actorId) {
        this.actorId = actorId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    @Column(nullable = false, length = 64)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Boolean hasAward;

    @Column(nullable = false)
    private Integer noOfMoviesWorkedOn;

    @Column(name = "createdBy", nullable = false)
    private Integer createdBy;

    @Column(nullable = false)
    private LocalDateTime createOn = LocalDateTime.now();

    @Column(name = "changedBy")
    private Integer changedBy;

    @Column()
    private LocalDateTime changedOn;

    @Column(name = "deletedBy")
    private Integer deletedBy;

    @Column
    private LocalDateTime deletedOn;
}
