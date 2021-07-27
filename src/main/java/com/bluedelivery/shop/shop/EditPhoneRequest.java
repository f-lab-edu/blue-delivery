package com.bluedelivery.shop.shop;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.bluedelivery.common.RegexConstants;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class EditPhoneRequest {
    @Pattern(regexp = RegexConstants.SHOP_PHONE, message = "잘못된 번호 형식")
    @NotBlank
    private String phone;
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
