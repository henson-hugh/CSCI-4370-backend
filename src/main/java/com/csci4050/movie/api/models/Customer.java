package com.csci4050.movie.api.models;

import javax.persistence.Entity;

import org.springframework.http.client.support.BasicAuthorizationInterceptor;

@Entity
public class Customer extends User{

    //Personal Info
    int Id;
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
        street = lStreet;
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
    public String getStreet(){
        return street;
    }
    public String getCity(){
        return city;
    }
    public String getState(){
        return state;
    }
    public int getZip(){
        return zip;
    }

    public String getPassword() {
        return password;
    }
    /*
    public Object getConfirmedPassword() {
        return confirmedPassword;
    }
    */
    public Long getCard(){
        return card;
    }
    public String getExp(){
        return exp;
    }
    public String getBStreet(){
        return bstreet;
    }
    public String getBCity(){
        return bcity;
    }
    public String getBState(){
        return bstate;
    }
    public int getBZip(){
        return bzip;
    }
    public Boolean getPromotion(){
        return promotion;
    }
    public int getId(){
        return Id;
    }

    //Setters
    public void setFirstName(String fname) {
        this.fname = fname;
    }
    public void setLastName(String lname) {
        this.lname = lname;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setStreet(String street){
        this.street = street;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setState(String state){
        this.state = state;
    }
    public void setZip(int zip){
        this.zip = zip;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCard(Long card){
        this.card = card;
    }
    public void setExp(String exp){
        this.exp = exp;
    }
    public void setBStreet(String bstreet){
        this.bstreet = bstreet;
    }
    public void setBCity(String bcity){
        this.bcity = bcity;
    }
    public void setBState(String bstate){
        this.bstate = bstate;
    }
    public void setBZip(int bzip){
        this.bzip = bzip;
    }
    public void setPromotion(boolean promotion){
        this.promotion = promotion;
    }
    public void setId(int Id){
        this.Id = Id;
    }
}