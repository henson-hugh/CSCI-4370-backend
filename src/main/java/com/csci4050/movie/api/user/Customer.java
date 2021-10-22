package com.csci4050.movie.api.user;

public class Customer extends User{
    
    //Personal Info
    String fName;
    String lName;
    String phone;
    String email;

    //Location Info
    String street;
    String city;
    String state;
    int zip;

    //Payment Info
    Long cardNum;
    String expDate;
    String billStreet;
    String billCity;
    String billState;
    int billZip;

    //Extra Info
    Boolean promotion;

    public Customer() {};

    //Required
    public Customer(Long id,String uName, String pwd, Status status, 
    String firstName, String lastName, String phoneNum, String emailAdd) {
        super(id, uName, pwd, status);
        fName = firstName;
        lName = lastName;
        phone = phoneNum;
        email = emailAdd;
    }

    @Override
    public String toString() {
        return "hi";
    }

    //Getters
    public String getFirstName() {
        return fName;
    }
    public String getLastName() {
        return lName;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
}
