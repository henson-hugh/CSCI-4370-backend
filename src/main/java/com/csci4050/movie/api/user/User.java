package com.csci4050.movie.api.user;

public class User {

    public enum Status {
        active,
        inactive
    };

    Long userId;
    Status userStatus;
    String userName;
    String password;

    

    public User(){};

    public User(Long id, String uName, String pwd, Status status) {
        userId = id;
        userStatus = status;
        userName = uName;
        password = pwd;
    }

    
    public  Boolean isAdmin() {
        return false;
    }

    //Getters
    public Long getid() {
        return userId;
    }
    public Status getStatus() {
        return userStatus;
    }
    public String getUserName() {
        return userName;
    }
    public String getpassword() {
        return password;
    }

    //Setters
    public void setID(Long id) {
        userId = id;
    }
    public void setStatus (Status status) {
        userStatus = status;
    }
    public void setUserName (String uName) {
        userName = uName;
    }
    public void setPassword(String pwd) {
        password = pwd;
    }

    //ToString
    @Override
    public String toString() {
        return "User{" + "status: " + userStatus + " |ID: " + userId + " |pass: " + password + "}";
    }
}
