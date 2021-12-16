package com.csci4050.movie.api.seat;

import com.csci4050.movie.api.booking.SeatRepository;
import com.csci4050.movie.api.model.Seat;
import com.csci4050.movie.api.showing.ShowingDto;
import com.csci4050.movie.api.showroom.ShowroomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    @Autowired
    private SeatRepository seatRepository;


    public List<Seat> getSeatsByRoomId(int sid) {
        return seatRepository.findAllByShowroomId(sid);

    }


    public List<Seat> getSeatsByShowingDateAndShowingTime(LocalDate date, LocalTime time) {
            return seatRepository.findAllByShowingDateAndShowingTime(date, time);
    }


    public Optional<Seat> saveSeat(SeatDto seatDto){
    Seat s = new Seat();
        s.setShowingDate(seatDto.getShowingDate());
        s.setShowingTime(seatDto.getShowingTime());
        s.setSid((seatDto.getSid()));
        s.setShowroomId(seatDto.getShowroom());
        seatRepository.save(s);
        return Optional.of(s);
    }
}