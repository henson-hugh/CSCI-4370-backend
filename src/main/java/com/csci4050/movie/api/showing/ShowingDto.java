package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Customer;
import lombok.Data;

import javax.persistence.Column;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowingDto {
    private int sid;

    private int movieid;

    private int roomid;

    private LocalTime time;

    private LocalDate date;

}

