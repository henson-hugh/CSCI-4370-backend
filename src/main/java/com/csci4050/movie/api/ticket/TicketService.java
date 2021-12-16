package com.csci4050.movie.api.ticket;

import com.csci4050.movie.api.model.Ticket;
import com.csci4050.movie.api.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TicketService {

    public void selectTicketType(Ticket ticket, String type) {
        ticket.setType(type);
    }
}
