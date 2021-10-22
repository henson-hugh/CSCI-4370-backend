package com.csci4050.movie.api.user;

public class Admin extends User{
    


    @Override
    public Boolean isAdmin() {
        return true;
    }
}
