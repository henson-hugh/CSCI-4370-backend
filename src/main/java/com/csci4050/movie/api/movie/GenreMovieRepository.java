package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.GenreMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreMovieRepository extends CrudRepository<GenreMovie, Integer> {
}
