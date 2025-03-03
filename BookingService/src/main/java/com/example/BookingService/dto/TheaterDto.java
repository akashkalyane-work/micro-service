package com.example.BookingService.dto;

import com.example.BookingService.entity.Theater;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterDto {

    public TheaterDto(int theaterId, String theaterName, Boolean isMultiScreen) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.isMultiScreen = isMultiScreen;
    }

    private int theaterId;
    private String theaterName;
    private Boolean isMultiScreen;
    private List<TheaterScreenDto> theaterScreenList;

    public static TheaterDto mapToDto(Theater theater){
        return new TheaterDto(
                theater.getTheaterId(),
                theater.getTheaterName(),
                theater.getIsMultiScreen(),
                theater.getTheaterScreens()
                        .stream().map(TheaterScreenDto::mapToDto).toList()
        );
    }

}
