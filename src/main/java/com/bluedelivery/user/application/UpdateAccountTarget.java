package com.bluedelivery.user.application;

import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.bluedelivery.common.RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAccountTarget {
    private final Long id;
    private final String nickname;
    private final String phone;
    private final LocalDate dateOfBirth;
}
