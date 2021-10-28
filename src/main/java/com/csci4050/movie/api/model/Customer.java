package com.csci4050.movie.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cid;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "payment_card")
    private String paymentCard;

    @Column(name = "active", columnDefinition = "boolean default false")
    private boolean active;

    @Column(name = "type", columnDefinition = "varchar(10) default 'customer'")
    private String type = "customer";

    @Column(name = "vcode")
    private String vcode;
}

