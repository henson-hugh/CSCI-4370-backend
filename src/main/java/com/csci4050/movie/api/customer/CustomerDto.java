package com.csci4050.movie.api.customer;

import lombok.Data;

import javax.persistence.Column;

@Data
public class CustomerDto {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String street;

    private String city;

    private String state;

    private String zip;

    private String paymentCard;

    private String expDate;

    private boolean active;

    private String type;

    private String vcode;

    private boolean getPromo;
}
