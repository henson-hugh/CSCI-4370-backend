package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.MovieCard;
import com.csci4050.movie.api.model.Genre;
import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import com.csci4050.movie.api.movie.MovieService;

@RestController
@RequestMapping("/showing")
public class ShowingController {
    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieService movieService;

    @PostMapping("/movie/home")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> get4Movies() {

        List<Movie> movieList = movieService.getAllMovies();
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < 4; i++) {
            if (movieList.get(i) != null) {
                    List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                    List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                    MovieCard mc = new MovieCard();
                    mc.setMovie(movieList.get(i));
                    mc.setGenres(genres);
                    mc.setShowings(showings);
                    movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);

        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }

    @PostMapping("/{id}")
    public Optional<Movie> searchMoviesById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

//    @RequestMapping("/searchMovieByGenre")
//    public List<Movie> searchMoviesByGenre(@PathVariable String genre) {
//        return movieService.getMovieByGenre(genre);
//    }

    @RequestMapping("/search/director")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByDirector(@RequestBody String director) {
        List<Movie> movieList = movieService.getMovieByDirector(director);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }
    @RequestMapping("/search/producer")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByProducer(@RequestBody String producer) {
        List<Movie> movieList = movieService.getMovieByProducer(producer);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }
//    @RequestMapping("/searchMovieByCast")
//    public List<Movie> searchMoviesByCast(@PathVariable String Cast) {
//        return movieService.getMovieByCast(Cast);
//    }

    @RequestMapping("/searchMovieByShowingNow")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Movie> searchMoviesByShowingNow(@PathVariable LocalDate date) {
        return movieService.getMovieByShowingNow(date);
    }

    @RequestMapping("/searchMovieByComingSoon")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Movie> searchMoviesByComingSoon(@PathVariable LocalDate date) {
        return movieService.getMovieByComingSoon(date);
    }

    @RequestMapping("/searchMoviesByDate")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Movie> searchMoviesByDate(@PathVariable LocalDate date) {
        return movieService.getMovieByDate(date);
    }



}


