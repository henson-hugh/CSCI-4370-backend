package com.csci4050.movie.api.showing;

import lombok.Data;

import javax.persistence.Column;

@Data
public class ShowingDto {
    private int sid;

    private String movieid;

    private String roomid;

    private String time;

    private String date;

    private String duration;
}
