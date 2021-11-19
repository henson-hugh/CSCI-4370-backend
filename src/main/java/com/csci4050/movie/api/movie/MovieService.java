package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.showing.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Optional<Movie> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public Optional<Movie> getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public Optional<Movie> saveMovie(Movie movie) {
        movieRepository.save(movie);
        return Optional.empty();
    }

    public Optional<Movie> editMovie(Movie movie) {
        Optional<Movie> exist = movieRepository.findById(movie.getMid());
        if (exist.isPresent()) {
            Movie newMovie = exist.get();
            newMovie.setDirector(movie.getDirector());
            newMovie.setTitle(movie.getTitle());
            newMovie.setProducer(movie.getProducer());
            newMovie.setSynopsis(movie.getSynopsis());
            newMovie.setDuration(movie.getDuration());
            newMovie.setTrailerpic(movie.getTrailerpic());
            newMovie.setTrailervid(movie.getTrailervid());
            newMovie.setRating(movie.getRating());

            movieRepository.save(newMovie);
            return exist;
        }

        return Optional.empty();
    }

    public Optional<Movie> removeMovie(Movie movie) {
        movieRepository.delete(movie);
        return Optional.empty();
    }

    //Genre
    public Optional<String> getGenreByName(String genre) {
        Optional<Genre> g = genreRepository.findByName(genre);
        if (g.isPresent()) {
            return Optional.of(g.get().getName());
        }
        return Optional.empty();
    }

    public Optional<Genre> addGenre(String genre, int mid) {
        Genre g = new Genre();
        g.setMovieid(mid);
        g.setName(genre);
        // check if overlaps exist
        Optional<Genre> check = genreRepository.findByNameAndMovieid(genre, mid);
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

//    public List<Movie> getMovieByGenre(String genre) {
//        return genreRepository.findAllByName(genre);
//    }

    public List<Movie> getMovieByDirector(String director) {
        return movieRepository.findAllByDirector(director);
    }

    public List<Movie> getMovieByProducer(String producer) {
        return movieRepository.findAllByProducer(producer);
    }
/*
    public List<Movie> getMovieByCast(String cast) {
        return movieRepository.findAllByCast(cast);
    }
*/

    public List<Movie> getMovieByShowingNow(LocalDate date) {
        List<Showing> showingList = new ArrayList<Showing>(showingRepository.findAllByDate(date));
        List<Movie> movies = new ArrayList<Movie>();
        LocalDate today = LocalDate.now();

        for (Showing showing : showingList) {
            if(today.compareTo(showing.getDate()) <= 0 && showing.getDate().compareTo(today.plusWeeks(2)) > 0 ){
                movies.add(movieRepository.findById(showing.getMovieid()).get());
            }
        }
        return movies;
    }

    public List<Movie> getMovieByComingSoon(LocalDate date) {
        List<Showing> soonList = new ArrayList<Showing>(showingRepository.findAllByDate(date));
        List<Movie> movies = new ArrayList<Movie>();
        LocalDate today = LocalDate.now();
        for (Showing showing : soonList) {
            if(showing.getDate().compareTo(today.plusWeeks(2)) <= 0 ){
                movies.add(movieRepository.findById(showing.getMovieid()).get());
            }
        }
        return movies;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public List<Genre> getGenreByMovieid(int mid) {
        return genreRepository.findAllByMovieid(mid);
    }

    //public List<Movie> getAll(){
      //  return
    //}

    /*
    public List<Movie> getMovieByCast(String cast) {

        //Get all movies
        // loop thorough and look for specific cast members
        // return a list of movies that have the cast member in it
        List<Movie> movies = movieRepository.findAll();
        List<Cast> c = new ArrayList<Cast>();
        for(Cast cast : movies){

    public List<Movie> getMovieByCast(String name) {
        List<Cast> castlist = castRepository.findAllByName(name);
        List<Movie> movieList = new ArrayList<Movie>();
        for (Cast c : castlist) {
            Movie m = movieRepository.findByMid(c.getMovieid());
            movieList.add(m);
        }
        return movieList;
    }

    public List<Movie> getMovieByGenre(String genre) {
        List<Genre> genrelist = genreRepository.findAllByGenre(genre);
        List<Movie> movieList = new ArrayList<Movie>();
        for (Genre g : genrelist) {
            Movie m = movieRepository.findByMid(g.getMovieid());
            movieList.add(m);
        }
        return movieList;
    }

}
