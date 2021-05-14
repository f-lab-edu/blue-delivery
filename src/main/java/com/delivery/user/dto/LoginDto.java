package com.delivery.user.dto;

public class LoginDto {

    private Long userId;
    private String email;
    private String userPassword;

    public LoginDto(Long id, String email, String userPassword) {
        this.userId = id;
        this.email = email;
        this.userPassword = userPassword;
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
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
