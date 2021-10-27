package com.csci4050.movie.api.customer;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class CustomerDto {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private String street;

    private String city;

    private String state;

    private int zip;

    private long cardNum;

    private String expDate;

    private String billStreet;

    private String billCity;

    private String billState;

    private String billZip;

    private boolean isAdmin;
}
