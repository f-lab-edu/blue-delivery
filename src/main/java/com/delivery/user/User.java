package com.delivery.user;

public class User {

    private String email;
    private String password;
    private String nickname;
    private String phone;

    public User(String email, String password, String nickname, String phone) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
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

}
