package com.csci4050.movie.api.paymentCard;

import com.csci4050.movie.api.model.PaymentCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentCardRepository extends CrudRepository<PaymentCard, Integer> {
    List<PaymentCard> findAllByCustomerid(int customerid);
    Optional<PaymentCard> findByCustomerid(int customerid);
}
