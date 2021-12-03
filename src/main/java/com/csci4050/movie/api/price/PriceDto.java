package com.csci4050.movie.api.price;

import lombok.Data;

@Data
public class PriceDto {

    private int pid;

    private int aTicket;

    private int cTicket;

    private int sTicket;

    private int fee;
}
