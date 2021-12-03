package com.csci4050.movie.api.booking;

import lombok.Data;

@Data
public class BookingDto {

    private int bid;

    private int customerId;

    private String creditCard;

}
