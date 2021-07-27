package com.bluedelivery.common.authentication.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class LoginTarget {
    private final String email;
    private final String password;
}


