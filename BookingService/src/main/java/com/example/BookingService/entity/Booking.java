package com.example.BookingService.entity;

import com.example.BookingService.dto.MovieResponseDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// Booking Entity
@Entity
@Table(name = "Booking")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;
    
    @Column(name = "MovieId", nullable = false)
    private Integer movie;

    @ManyToOne
    @JoinColumn(name = "TheaterScreenId", nullable = false)
    private TheaterScreen theaterScreen;

    @Column(nullable = false)
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "SlotId", nullable = false)
    private Slot slot;

    @Column(nullable = false)
    private Integer numberOfSlot;

    @JoinColumn(name = "CreatedBy", nullable = false)
    private Integer createdBy;

    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT NOW()::TIMESTAMP")
    private LocalDateTime createOn;

    @JoinColumn(name = "ChangedBy")
    private Integer changedBy;

    @Column
    private LocalDateTime changedOn;

    @JoinColumn(name = "DeletedBy")
    private Integer deletedBy;

    @Column
    private LocalDateTime deletedOn;

    transient MovieResponseDto movieDB;


}

