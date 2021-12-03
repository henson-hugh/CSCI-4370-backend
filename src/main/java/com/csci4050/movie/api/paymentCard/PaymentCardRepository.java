package com.csci4050.movie.api.paymentCard;

import com.csci4050.movie.api.model.PaymentCard;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaymentCardRepository extends CrudRepository<PaymentCard, Integer> {
    List<PaymentCard> findAllByCustomerid(int customerid);
}
