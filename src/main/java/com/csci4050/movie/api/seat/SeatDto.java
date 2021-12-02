package com.csci4050.movie.api.seat;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;


@Data
public class SeatDto {

    private int sid;

    private boolean taken;

    private int showroom;

    private LocalDate showingDate;

    private LocalTime showingTime;


}

