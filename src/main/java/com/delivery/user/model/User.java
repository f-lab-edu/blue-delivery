package com.delivery.user.model;

public class User {

    private Long userId;
    private String email;
    private String userPassword;

    public User(Long userId, String email, String userPassword) {
        this.userId = userId;
        this.email = email;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
