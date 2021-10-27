package com.csci4050.movie.api.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long userId;
    @Enumerated(value = EnumType.STRING)
    UserStatus userStatus;
    String userName;
    String password;



    public User(){};

    public User(String uName, String pwd, UserStatus status) {
        userName = uName;
        password = pwd;
        userStatus = status;
    }

    public abstract Boolean isAdmin();
}