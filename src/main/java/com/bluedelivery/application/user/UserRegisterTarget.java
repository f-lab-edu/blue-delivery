package com.bluedelivery.application.user;

import java.time.LocalDate;

import com.bluedelivery.domain.user.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterTarget {
    private final String email;
    private final String nickname;
    private final String phone;
    private final String password;
    private final LocalDate dateOfBirth;
    
    public User toEntity() {
        return new User(email, nickname, phone, password, dateOfBirth);
    }
}
