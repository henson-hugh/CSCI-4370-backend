package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.CastMovie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CastMovieRepository extends CrudRepository<CastMovie, Integer> {
}
