package com.example.BookingService.dto;

import com.example.BookingService.entity.TheaterScreen;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheaterScreenDto {

    private int theaterScreenId;
    private TheaterDto theater;
    private String screenName;

    public static TheaterScreenDto mapToDto(TheaterScreen theaterScreen){
        return new TheaterScreenDto(
                theaterScreen.getTheaterScreenId(),
                new TheaterDto(
                        theaterScreen.getTheater().getTheaterId(),
                        theaterScreen.getTheater().getTheaterName(),
                        theaterScreen.getTheater().getIsMultiScreen()
                        ),
                theaterScreen.getScreenName()
        );
    }
}
