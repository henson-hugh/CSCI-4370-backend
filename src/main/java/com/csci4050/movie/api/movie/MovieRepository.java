package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {
    Optional<Movie> findByTitle(String title);
    Movie findByMid(int id);
    List<Movie> findAllByGenre(String genre);
    List<Movie> findAllByDirector(String director);
    List<Movie> findAllByProducer(String producer);
    List<Movie> findAllByCategory(String category);
    //List<Movie>
    //List<Movie> findAllByCast(String cast);  // needs to be changed into the CAST dto


}
