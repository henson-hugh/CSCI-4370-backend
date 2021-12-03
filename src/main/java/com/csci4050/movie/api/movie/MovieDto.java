package com.csci4050.movie.api.movie;

import lombok.Data;

@Data
public class MovieDto {

    private int mid;

    private String title;

    private String director;

    private String producer;

    private String synopsis;

    private String trailerpic;

    private String trailervid;

    private int duration;

    private String rating;
}
