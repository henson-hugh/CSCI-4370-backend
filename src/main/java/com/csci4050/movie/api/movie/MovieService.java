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
    private CastMovieRepository castMovieRepository;

    @Autowired
    private GenreMovieRepository genreMovieRepository;

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


    public Optional<Genre> addGenre(String genre) {
        Genre g = new Genre();
        g.setGenre(genre);
        genreRepository.save(g);
        return Optional.of(g);
    }

    public Optional<GenreMovie> addGenreMovie(int mid, String genre) {
        Genre g = genreRepository.findByGenre(genre).get();
        GenreMovie gm = new GenreMovie();
        gm.setGenreid(g.getGid());
        gm.setMovieid(mid);
        // check if association exists
        List<GenreMovie> gmes = genreMovieRepository.findByMovieid(mid);
        for (GenreMovie gme : gmes) {
            if (gme.getGenreid() == g.getGid()) {
                return Optional.empty();
            }
        }
        genreMovieRepository.save(gm);
        return Optional.of(gm);
    }

    //Cast
    public Optional<String> getCastByName(String name) {
        Optional<Cast> c = castRepository.findByName(name);
        if (c.isPresent()) {
            return Optional.of(c.get().getName());
        }
        return Optional.empty();
    }

    public Optional<Cast> addCast(String name) {
        Cast c = new Cast();
        c.setName(name);
        castRepository.save(c);
        return Optional.of(c);
    }

    public Optional<CastMovie> addCastMovie(int mid, String name) {
        Cast c = castRepository.findByName(name).get();
        CastMovie cm = new CastMovie();
        cm.setCastid(c.getCaid());
        cm.setMovieid(mid);
        // check if associations exist
        List<CastMovie> cmes = castMovieRepository.findByMovieid(mid);
        for (CastMovie cme : cmes) {
            if (cme.getCastid() == c.getCaid()) {
                return Optional.empty();
            }
        }
        castMovieRepository.save(cm);
        return Optional.of(cm);
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
