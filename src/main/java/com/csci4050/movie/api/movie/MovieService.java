package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.showing.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.swing.text.html.Option;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CastRepository castRepository;

    @Autowired
    private GenreRepository genreRepository;


    @Autowired
    private ShowingRepository showingRepository;

    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Optional<Movie> saveMovie(Movie movie) {
        movieRepository.save(movie);
        return Optional.empty();
    }

    public Optional<Movie> removeMovie(Movie movie) {
        movieRepository.delete(movie);
        return Optional.empty();
    }

    //Genre
    public Optional<String> getGenreByGenre(String genre) {
        Optional<Genre> g = genreRepository.findByGenre(genre);
        if (g.isPresent()) {
            return Optional.of(g.get().getGenre());
        }
        return Optional.empty();
    }

    public Optional<Genre> addGenre(String genre, int mid) {
        Genre g = new Genre();
        g.setMovieid(mid);
        g.setGenre(genre);
        // check if overlaps exist
        Optional<Genre> check = genreRepository.findByGenreAndMovieid(genre, mid);
        if (check.isPresent()) {
            return Optional.empty();
        }
        genreRepository.save(g);
        return Optional.of(g);
    }

    //Cast
    public Optional<String> getCastByName(String name) {
        Optional<Cast> c = castRepository.findByName(name);
        if (c.isPresent()) {
            return Optional.of(c.get().getName());
        }
        return Optional.empty();
    }

    public Optional<Cast> addCast(String name, int mid) {
        Cast c = new Cast();
        c.setMovieid(mid);
        c.setName(name);
        // check if overlaps exist
        Optional<Cast> check = castRepository.findByNameAndMovieid(name, mid);
        if (check.isPresent()) {
            return Optional.empty();
        }
        castRepository.save(c);
        return Optional.of(c);
    }

    public List<Movie> getMovieByGenre(String genre) {
        return movieRepository.findAllByGenre(genre);
    }

    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findAllByDirector(director);
    }

    public List<Movie> getMovieByProducer(String producer) {
        return movieRepository.findAllByProducer(producer);
    }

    public List<Movie> getMovieByCast(String cast) {
        return movieRepository.findAllByCast(cast);
    }

    public List<Movie> getMovieByCategory(String category) {
        return movieRepository.findAllByCategory(category);
    }

    public List<Movie> getMovieByDate(LocalDate date) {
        List<Showing> showingList = new ArrayList<Showing>(showingRepository.findAllByDate(date));
        List<Movie> movies = new ArrayList<Movie>();
        //LocalDate today = new LocalDate();

        for (Showing showing : showingList) {
            //     if(today.compareTo(showing.getDate()) <=0 && showing.getDate().compareTo(today.plusWeeks(2)) < 0 ){
            //movies.add(Showing.)
            //    }
        }
        return movies;
    }
}
