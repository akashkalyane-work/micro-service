package com.example.BookingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {
    private int movieId;
    private int theaterScreenId;
    private int price;
    private int slotId;
    private int numberOfSlot;
    private int createdBy;
}