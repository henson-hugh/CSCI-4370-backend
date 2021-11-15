package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Showing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowingRepository extends CrudRepository<Showing, Integer> {
    Optional<Showing>[] findAllByDate(String date);
}
