package com.csci4050.movie.api.showroom;

import com.csci4050.movie.api.model.Showroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowroomRepository extends CrudRepository<Showroom, Integer> {
    Optional<Showroom> findByRid(int rid);
}
