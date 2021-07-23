package com.delivery.user;

import java.io.Serializable;
import java.time.LocalDate;

import com.delivery.user.domain.User;

import lombok.Builder;

@Builder
public class Authentication implements Serializable {
    public static String AUTH_VALUE = "auth"; // session에 key로 스트링을 넣을 때 사용
    private Long id;
    private String email;
    private String nickname;
    private String phone;
    private LocalDate dateOfBirth;
    
    public Authentication() {
    }
    
    public Authentication(Long id, String email, String nickname, String phone, LocalDate dateOfBirth) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
    }
    
    public static Authentication from(User user) {
        return new Authentication(user.getId(), user.getEmail(),
                user.getNickname(), user.getPhone(), user.getDateOfBirth());
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
