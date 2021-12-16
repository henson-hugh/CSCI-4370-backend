package com.csci4050.movie.api.booking;

import com.csci4050.movie.api.model.Seat;
import com.csci4050.movie.api.seat.SeatDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends CrudRepository<Seat, Integer> {
    List<Seat> findAllByShowroomId(int sid);
    List<Seat> findAllByShowingDateAndShowingTime(LocalDate showingDate, LocalTime showingTime);
}