package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import com.csci4050.movie.api.movie.MovieService;
import java.util.Optional;

@RestController
@RequestMapping("/showing")
public class ShowingController {
    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieService movieService;

    @RequestMapping("/searchMovieByGenre")
    public List<Movie> searchMoviesByGenre(@PathVariable String genre) {
        return movieService.getMovieByGenre(genre);
    }
    @RequestMapping("/searchMovieByDirector")
    public List<Movie> searchMoviesByDirector(@PathVariable String director) {
        return movieService.getMovieByDirector(director);
    }
    @RequestMapping("/searchMovieByProducer")
    public List<Movie> searchMoviesByProducer(@PathVariable String producer) {
        return movieService.getMovieByProducer(producer);
    }
    @RequestMapping("/searchMovieByCast")
    public List<Movie> searchMoviesByCast(@PathVariable String Cast) {
        return movieService.getMovieByCast(Cast);
    }
    @RequestMapping("/searchMovieByCategory")
    public List<Movie> searchMoviesByCategory(@PathVariable String Category) {
        return movieService.getMovieByCategory(Category);
    }

    @RequestMapping("/searchMovieByDate")
    public List<Movie> searchMoviesByDate(@PathVariable LocalDate date) {
        return movieService.getMovieByDate(date);
    }

}


