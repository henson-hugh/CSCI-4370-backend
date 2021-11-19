package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
}
