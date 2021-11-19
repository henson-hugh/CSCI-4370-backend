package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    List<Movie> findAllByGenre(String genre);
    List<Movie> findAllByDirector(String director);
    List<Movie> findAllByProducer(String producer);
    List<Movie> findAllByCast(String cast);
    List<Movie> findAllByCategory(String category);
    //List<Movie> findAllByPlayingNow(String playingNow);
    //List<Movie> findAllByCommingSoon(String commingSoon);


}
