package com.csci4050.movie.api.paymentCard;

import com.csci4050.movie.api.model.Customer;
import com.csci4050.movie.api.model.Genre;
import com.csci4050.movie.api.model.Movie;
import com.csci4050.movie.api.model.PaymentCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentCardService {

    @Autowired
    PaymentCardRepository paymentCardRepository;

    public Optional<PaymentCard> getPaymentCardById(PaymentCard paymentCard) {
        return paymentCardRepository.findById(paymentCard.getPcid());
    }

    public List<PaymentCard> getAllPaymentCards(Customer customer) {
        return paymentCardRepository.findAllByCustomerid(customer.getCid());
    }

    public Optional<PaymentCard> savePaymentCard(PaymentCard paymentCard) {
        if (paymentCard.getExpDate().isBefore(LocalDate.now()) || paymentCard.getExpDate().isEqual(LocalDate.now())) {
            System.out.println("Payment card is expired and cannot be stored");
            return Optional.empty();
        } else {
            return Optional.of(paymentCardRepository.save(paymentCard));
        }

    }

    public Optional<PaymentCard> editPaymentCard(PaymentCard paymentCard) {
        Optional<PaymentCard> exist = paymentCardRepository.findById(paymentCard.getPcid());
        if (paymentCard.getExpDate().isBefore(LocalDate.now()) || paymentCard.getExpDate().isEqual(LocalDate.now())) {
            System.out.println("Payment card is expired and cannot be stored");
            return Optional.empty();
        } else if (exist.isPresent()) {
            PaymentCard newPaymentCard = exist.get();
            newPaymentCard.setCardNumber(paymentCard.getCardNumber());
            newPaymentCard.setExpDate(paymentCard.getExpDate());
            newPaymentCard.setStreet(paymentCard.getStreet());
            newPaymentCard.setCity(paymentCard.getCity());
            newPaymentCard.setState(paymentCard.getState());
            newPaymentCard.setZip(paymentCard.getZip());

            return Optional.of(paymentCardRepository.save(newPaymentCard));
        } else {
            return Optional.empty();
        }
    }
}
