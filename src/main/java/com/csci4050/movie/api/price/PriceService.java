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
        newPrice.setPid(1);
        newPrice.setSticket(price.getSticket());
        newPrice.setAticket(price.getAticket());
        newPrice.setCticket(price.getCticket());
        newPrice.setFee(price.getFee());
        return Optional.of(priceRepository.save(newPrice));
    }

    public double getPrice(String type) {
        double price = 0.0;
        Price ticket = priceRepository.findById(1).get();
        if (type.equals("adult")) {
            price = ticket.getAticket();
        }
        if (type.equals("child")) {
            price = ticket.getCticket();
        }
        if (type.equals("elderly")) {
            price = ticket.getSticket();
        }
        return price;
    }

}
