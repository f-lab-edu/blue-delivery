package com.bluedelivery.api.user;

import com.bluedelivery.application.user.AddAddressTarget;

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
