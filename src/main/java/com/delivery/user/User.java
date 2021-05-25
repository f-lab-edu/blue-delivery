package com.delivery.user;

import java.time.LocalDate;

public class User {
    
    private String email;
    private String nickname;
    private String phone;
    private String password;
    private LocalDate dateOfBirth;
    
    public User(String email, String nickname, String phone, String password, LocalDate dateOfBirth) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    
    public boolean checkPasswordEquality(String password) {
        return this.password.equals(password);
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
}
