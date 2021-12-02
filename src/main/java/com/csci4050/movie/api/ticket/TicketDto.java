package com.csci4050.movie.api.ticket;

import lombok.Data;

import java.time.LocalTime;

@Data
public class TicketDto {

    private int tid;

    private LocalTime bookingId;

    private String type;

    private double price;

    private int seatId;

}
