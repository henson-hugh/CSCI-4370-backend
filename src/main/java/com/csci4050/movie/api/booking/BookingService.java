package com.csci4050.movie.api.booking;

import com.csci4050.movie.api.model.Booking;
import com.csci4050.movie.api.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public List<Booking> getBookingHistory(Customer customer) {
        return bookingRepository.findAllByCustomerid(customer.getCid());
    }
}
