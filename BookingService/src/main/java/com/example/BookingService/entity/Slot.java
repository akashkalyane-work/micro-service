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
public class Slot {

    public Slot(int slotId){
        this.slotId = slotId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int slotId;

    @Column(nullable = false, unique = true, length = 20)
    private String slotName;

    @Column(nullable = false)
    private boolean isAvailable;

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
