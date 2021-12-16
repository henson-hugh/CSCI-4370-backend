package com.csci4050.movie.api.booking;

import com.csci4050.movie.api.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findAllByCustomerid(int id);
}