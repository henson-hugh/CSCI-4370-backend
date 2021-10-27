package com.csci4050.movie.api.customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "customers")
public class CustomerDto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int cid;

    @Column(name = "fname")
    private String firstName;

    @Column(name = "lname")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "Street")
    private String Street;

    @Column(name = "city")
    private String city;
    
    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private int zip;

    @Column(name = "cardNum")
    private String cardNum;

    @Column(name = "expDate")
    private String expDate;
    
    @Column(name = "BStreet")
    private String BStreet;

    @Column(name = "BCity")
    private String BCity;
    
    @Column(name = "BState")
    private String BState;

    @Column(name = "BZip")
    private int BZip;
    
    @Column(name = "promotions")
    private String promotions;


}