package com.csci4050.movie.api.movie;

import com.csci4050.movie.api.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {
    Optional<Genre> findByName(String name);
    Optional<Genre> findByNameAndMovieid(String name, int movieid);
    List<Genre> findAllByName(String name);
}
