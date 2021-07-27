package com.bluedelivery.application.user;

import java.time.LocalDate;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAccountTarget {
    private final Long id;
    private final String nickname;
    private final String phone;
    private final LocalDate dateOfBirth;
}
