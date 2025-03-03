package com.example.BookingService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheaterScreen {

    public TheaterScreen(int theaterScreenId){
        this.theaterScreenId = theaterScreenId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterScreenId;

    @ManyToOne
    @JoinColumn(name = "theaterId", nullable = false)
    private Theater theater;

    @Column(nullable = false, length = 20)
    private String screenName;

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
