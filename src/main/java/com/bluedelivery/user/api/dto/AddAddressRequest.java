package com.bluedelivery.user.api.dto;

import com.bluedelivery.user.application.AddAddressTarget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddAddressRequest {
    private String buildingManagementNumber;
    private String detail;
    
    public AddAddressTarget toParam(Long id) {
        return new AddAddressTarget(id, buildingManagementNumber, detail);
    }
}
