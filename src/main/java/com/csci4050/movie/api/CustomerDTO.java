package com.csci4050.movie.api;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CustomerDTO {
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String address;

    private LocalDate creationDate;
}
