package com.delivery.user;

public class LoginDto {

    private String email;
    private String userPassword;

    public LoginDto(Long id, String email, String userPassword) {
        this.email = email;
        this.userPassword = userPassword;
    }

    public String getUserId() {
        return email;
    }

    public void setUserId(String userId) {
        this.email = userId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
