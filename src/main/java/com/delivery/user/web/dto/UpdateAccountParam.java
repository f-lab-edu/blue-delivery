package com.delivery.user.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;
import com.delivery.utility.address.Address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAccountParam {
    private final Long id;
    private final String email;
    private final String nickname;
    private final String phone;
    private final String password;
    private final Address address;
    
    @Getter
    @RequiredArgsConstructor
    public static class UpdateAccountRequest {
        
        private final String email;
        
        @NotBlank
        private final String nickname;
        
        @NotBlank
        @Pattern(regexp = RegexConstants.PHONE,
                message = "01로 시작하는 10-11자리 숫자여야 합니다.")
        private final String phone;
        
        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD,
                message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
        private final String password;
        
        private final Address address;
    
        public UpdateAccountParam toParam(Long id) {
            return new UpdateAccountParam(id, email, nickname, phone, password, address);
        }
    }
}
