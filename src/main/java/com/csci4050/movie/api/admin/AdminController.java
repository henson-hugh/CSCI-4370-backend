package com.csci4050.movie.api.admin;

import com.csci4050.movie.api.model.Admin;
import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.Showing;
import com.csci4050.movie.api.movie.MovieDto;
import com.csci4050.movie.api.movie.MovieService;
import com.csci4050.movie.api.showing.ShowingDto;
import com.csci4050.movie.api.showing.ShowingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieService movieService;

    @Autowired
    private ShowingService showingService;

    // Add movie
    @PostMapping(value = "/movie/add")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);

        // check if movie exists
        Optional<Movie> movieExist = movieService.getMovieByTitle(movie.getTitle());
        if (movieExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(movieExist.get(), MovieDto.class));
        }

        movieService.saveMovie(movie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieDto);
    }

    // Remove movie
    @PostMapping(value = "/movie/remove")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<MovieDto> removeMovie(@RequestBody MovieDto movieDto) {
        Movie movie = modelMapper.map(movieDto, Movie.class);

        // check if it exists
        Optional<Movie> movieExist = movieService.getMovieByTitle(movie.getTitle());
        if (movieExist.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(modelMapper.map(movieExist.get(), MovieDto.class));
        }
        movieService.removeMovie(movie);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieDto);
    }

    // Schedule a movie (Create a showing)
    @PostMapping(value = "/movie/schedule")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ShowingDto> addShowing(@RequestBody ShowingDto showingDto) {
        Showing showing = modelMapper.map(showingDto, Showing.class);

        // check to make sure there are no time conflicts
        Optional<Showing> conflict = showingService.getConflictingShow(showing);
        if (!conflict.isPresent()) {
            showingService.saveShowing(showing);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(showingDto);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(showingDto);
    }



}
