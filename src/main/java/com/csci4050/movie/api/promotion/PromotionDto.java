package com.csci4050.movie.api.promotion;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PromotionDto {

    private int pid;

    private double discount;

    private String pcode;

    private LocalDate endDate;

    private String description;
}
