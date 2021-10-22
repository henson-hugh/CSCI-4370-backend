package com.csci4050.movie.api.Models;

import javax.persistence.Entity;

@Entity
public class Customer extends User{

    //Personal Info
    String fname;
    String lname;
    String phone;
    String email;

    //Location Info
    String street;
    String city;
    String state;
    int zip;

    //Payment Info
    Long card;
    String exp;
    String bstreet;
    String bcity;
    String bstate;
    int bzip;

    //Extra Info
    Boolean promotion;

    public Customer() {};

    //Required Fields
    public Customer(String uName, String pwd, UserStatus status,
    String firstName, String lastName, String phoneNum, String emailAdd) {
        super(uName, pwd, status);
        fname = firstName;
        lname = lastName;
        phone = phoneNum;
        email = emailAdd;
    }

    //Required + location
    public Customer(String uName, String pwd, UserStatus status,
    String firstName, String lastName, String phoneNum, String emailAdd,
    String lStreet, String lCity, String lState, int lZip) {
        super(uName, pwd, status);
        fname = firstName;
        lname = lastName;
        phone = phoneNum;
        email = emailAdd;
        street = lState;
        city = lCity;
        state = lState;
        zip = lZip;
    }

    //Required + payment
    public Customer(String uName, String pwd, UserStatus status,
    String firstName, String lastName, String phoneNum, String emailAdd,
    Long cNum, String expiration, String bStreet, String bCity, String bState, int bZip) {
        super(uName, pwd, status);
        fname = firstName;
        lname = lastName;
        phone = phoneNum;
        email = emailAdd;
        card = cNum;
        exp = expiration;
        bstreet = bStreet;
        bcity = bCity;
        bstate = bState;
        bzip = bZip;
    }
    //All
    public Customer(String uName, String pwd, UserStatus status,
    String firstName, String lastName, String phoneNum, String emailAdd,
    String lStreet, String lCity, String lState, int lZip,
    Long cNum, String expiration, String bStreet, String bCity, String bState, int bZip) {
        super(uName, pwd, status);
        fname = firstName;
        lname = lastName;
        phone = phoneNum;
        email = emailAdd;
        street = lState;
        city = lCity;
        state = lState;
        zip = lZip;
        card = cNum;
        exp = expiration;
        bstreet = bStreet;
        bcity = bCity;
        bstate = bState;
        bzip = bZip;
    }



    @Override
    public Boolean isAdmin() {
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + fname + " " + lname + "| Phone: " + phone + " | email: " + email;
    }

    //Getters
    public String getFirstName() {
        return fname;
    }
    public String getLastName() {
        return lname;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
}
