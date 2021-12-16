package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.MovieCard;
import com.csci4050.movie.api.model.Cast;
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
    }

    @PostMapping("/{id}")
    public Optional<Movie> searchMoviesById(@PathVariable int id) {
        return movieService.getMovieById(id);
    }

    @RequestMapping("/search/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchAllMovies() {
        List<Movie> movieList = movieService.getAllMovies();
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

    @RequestMapping("/search/genre")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByGenre(@RequestBody String genre) {
        List<Movie> movieList = movieService.getMovieByGenre(genre);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

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
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
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
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/search/cast")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByCast(@RequestBody String name) {
        List<Movie> movieList = movieService.getMovieByCast(name);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/search/title")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByTitle(@RequestBody String title) {
        List<Movie> movieList = movieService.getMoviesByTitle(title);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/search/rating")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByRating(@RequestBody String rating) {
        List<Movie> movieList = movieService.getMoviesByRating(rating);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/search/date")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByDate(@RequestBody String dateString) {
        LocalDate date = LocalDate.parse(dateString);

        List<Movie> movieList = movieService.getMovieByDate(date);
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < movieList.size(); i++) {
            if (movieList.get(i) != null) {
                List<Genre> genres = movieService.getGenreByMovieid(movieList.get(i).getMid());
                List<Showing> showings = showingService.getShowingsByMovieid(movieList.get(i).getMid());
                List<Cast> casts = movieService.getCastByMovieid(movieList.get(i).getMid());
                MovieCard mc = new MovieCard();
                mc.setMovie(movieList.get(i));
                mc.setGenres(genres);
                mc.setShowings(showings);
                mc.setCasts(casts);
                movieCard.add(mc);
            }
        }
        map.put("movies", movieCard);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }

    @RequestMapping("/movie/info")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> showMovieInfo(@RequestBody int mid) {
        Movie movie = movieService.getMovieById(mid).get();
        List<Showing> showings = showingService.getShowingsByMovieid(mid);
        List<Genre> genres = movieService.getGenreByMovieid(mid);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("movie", movie);
        map.put("showing", showings);
        map.put("genre", genres);
        return new ResponseEntity<Object>(map, HttpStatus.ACCEPTED);
    }



    @RequestMapping("/movie/home/now")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByShowingNow() {

        List<Movie> movieList = movieService.getMovieByShowingNow(LocalDate.now());
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();

        for (int i = 0; i < (movieList.size() < 4 ? movieList.size() : 4); i++) {
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

    @RequestMapping("/movie/home/soon")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Object> searchMoviesByComingSoon() {
        List<Movie> movieList = movieService.getMovieByComingSoon(LocalDate.now());
        Map<String, Object> map = new HashMap<String, Object>();
        List<MovieCard> movieCard = new ArrayList<MovieCard>();
        for (int i = 0; i < (movieList.size() < 4 ? movieList.size() : 4); i++) {
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

}


