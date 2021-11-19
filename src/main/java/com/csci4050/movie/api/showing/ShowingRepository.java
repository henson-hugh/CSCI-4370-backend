package com.csci4050.movie.api.showing;

import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.Showing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowingRepository extends CrudRepository<Showing, Integer> {
    List<Showing> findAllByDateAndRoomid(LocalDate date, int roomid);
    List<Showing> findAllByDate(LocalDate date);
    List<Showing> findAllByMovieid(int movieid);


}
