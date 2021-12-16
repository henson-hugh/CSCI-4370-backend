package com.csci4050.movie.api.booking;

import com.csci4050.movie.api.customer.CustomerDto;
import com.csci4050.movie.api.model.*;
import com.csci4050.movie.api.seat.SeatDto;
import com.csci4050.movie.api.seat.SeatService;
import com.csci4050.movie.api.showing.ShowingDto;
import com.csci4050.movie.api.showroom.ShowroomDto;
import com.csci4050.movie.api.ticket.TicketDto;
import com.csci4050.movie.api.ticket.TicketService;
import com.csci4050.movie.api.user.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
public class BookingController {

    SeatService seatService;

    TicketService ticketService;

    @PostMapping(value = "/findAllSeats")
    @CrossOrigin(origins = "http://localhost:4200")
    List<Seat> findAllSeats(@RequestBody ShowingDto showingDto) {
    return seatService.getSeatsByRoomId(showingDto.getSid());

    }

   @PostMapping(value = "/findTakenSeats")
    @CrossOrigin(origins = "http://localhost:4200")
    List<Seat> findTakenSeats(@RequestBody ShowingDto showingDto) {
        return seatService.getSeatsByShowingDateAndShowingTime(showingDto.getDate(), showingDto.getTime());
    }



    @PostMapping(value = "/selectTicketType")
    @CrossOrigin(origins = "http://localhost:4200")
    ResponseEntity<TicketDto> selectTicketType(@RequestBody TicketDto ticketDto, String type) {
        Ticket ticket = new Ticket();
        ticketService.selectTicketType(ticket, type);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(ticketDto);

    }


}