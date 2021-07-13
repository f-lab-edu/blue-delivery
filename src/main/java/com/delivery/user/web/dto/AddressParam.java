package com.delivery.user.web.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AddressParam {
    private final Long id;
    private final String buildingManagementNumber;
    private final String detail;
    
    @RequiredArgsConstructor
    @Getter
    public static class AddressRequest {
        private final String buildingManagementNumber;
        private final String detail;
    
        public AddressParam toParam(Long id) {
            return new AddressParam(id, buildingManagementNumber, detail);
        }
    }
}
