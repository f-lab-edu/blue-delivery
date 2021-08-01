package com.bluedelivery.api.user.adapter;

import com.bluedelivery.domain.user.User;

import lombok.Getter;

@Getter
public class CreatedUserDto {
    private String email;
    private String nickname;

    public CreatedUserDto(User user) {
        this.email = user.getEmail();
        this.nickname = user.getNickname();
    }
}
