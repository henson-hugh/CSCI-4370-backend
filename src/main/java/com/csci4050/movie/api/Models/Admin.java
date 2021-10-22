package com.csci4050.movie.api.Models;

import javax.persistence.Entity;

@Entity
public class Admin extends User{
    


    @Override
    public Boolean isAdmin() {
        return true;
    }

    public Admin () {};

    //User only (Testing)
    public Admin(String uName, String pwd, UserStatus status) {
        super(uName, pwd, status);
    }
}
