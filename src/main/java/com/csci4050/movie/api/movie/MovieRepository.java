package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    List<Movie> findAll();
    Optional<Movie> findByTitle(String title);
    List<Movie> findAllByDirector(String director);
    List<Movie> findAllByProducer(String producer);
    List<Movie> findAllByTitle(String title);
    List<Movie> findAllByRating(String rating);
}
