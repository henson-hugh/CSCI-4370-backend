package com.csci4050.movie.api.paymentCard;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PaymentCardDto {

    private int pcid;

    private int customerid;

    private String cardNumber;

    private LocalDate expDate;

    private String street;

    private String city;

    private String state;

    private String zip;
}
