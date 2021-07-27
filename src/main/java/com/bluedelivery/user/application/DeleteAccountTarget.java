package com.bluedelivery.user.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteAccountTarget {
    private final Long id;
    private final String email;
    private final String password;
}
