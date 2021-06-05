package com.delivery.restaurant;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;

public class EditPhoneDto {
    @Pattern(regexp = RegexConstants.SHOP_PHONE, message = "잘못된 번호 형식")
    @NotBlank
    private String phone;
    
    public EditPhoneDto() {
    }
    
    public EditPhoneDto(String phone) {
        this.phone = phone.replaceAll("-", "");
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
