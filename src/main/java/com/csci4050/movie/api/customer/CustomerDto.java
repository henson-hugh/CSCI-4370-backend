package com.csci4050.movie.api.customer;

import lombok.Data;

@Data
public class CustomerDto {
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private boolean active;

    private String type;

    private String verificationCode;
}
