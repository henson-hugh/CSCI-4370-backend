package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "payment_cards")
public class PaymentCard {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int pcid;

    @Column(name = "customerid")
    private int customerid;

    @Column(name = "cardNumber")
    private int cardNumber;

    @Column(name = "expDate")
    private LocalDate expDate;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;
}
