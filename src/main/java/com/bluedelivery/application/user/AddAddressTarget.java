package com.bluedelivery.application.user;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddAddressTarget {
    private final Long id;
    private final String buildingManagementNumber;
    private final String detail;
}
