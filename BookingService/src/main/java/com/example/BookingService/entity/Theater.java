package com.example.BookingService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int theaterId;

    @Column(nullable = false, unique = true, length = 20)
    private String theaterName;

    @Column(nullable = false)
    private Boolean isMultiScreen;

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

    @OneToMany(mappedBy = "theater")
    private List<TheaterScreen> theaterScreens;
}
