package com.csci4050.movie.api.price;

import com.csci4050.movie.api.model.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public Optional<Price> editPrice(Price price) {
        Price newPrice = price;
        newPrice.setSTicket(price.getSTicket());
        newPrice.setATicket(price.getATicket());
        newPrice.setCTicket(price.getCTicket());
        return Optional.of(priceRepository.save(newPrice));
    }

}
