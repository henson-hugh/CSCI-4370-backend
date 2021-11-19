package com.csci4050.movie.api;

import com.csci4050.movie.api.model.Genre;
import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.Showing;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieCard {
    Movie movie;
    List<Genre> genres;
    List<Showing> showings;
}
