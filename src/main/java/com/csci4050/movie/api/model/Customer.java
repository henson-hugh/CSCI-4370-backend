package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int cid;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address", unique = true)
    private String address;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private int zip;

    @Column(name = "cnum")
    private long cardNum;

    @Column(name = "exdate")
    private String expDate;

    @Column(name = "bstreet")
    private String billStreet;

    @Column(name = "bcity")
    private String billCity;

    @Column(name = "bstate")
    private String billState;

    @Column(name = "bzip")
    private String billZip;

    @Column(name = "admin")
    private boolean isAdmin;

}
