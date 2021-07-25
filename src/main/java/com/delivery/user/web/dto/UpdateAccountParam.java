package com.delivery.user.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;
import com.delivery.utility.address.Address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateAccountParam {
    private final Long id;
    private final String nickname;
    private final String phone;
    private final LocalDate dateOfBirth;
    
    @Getter
    @RequiredArgsConstructor
    public static class UpdateAccountRequest {
        
        private final String nickname;
        
        @Pattern(regexp = RegexConstants.PHONE,
                message = "01로 시작하는 10-11자리 숫자여야 합니다.")
        private final String phone;
        @Past
        private final LocalDate dateOfBirth;
        
        public UpdateAccountParam toParam(Long id) {
            return new UpdateAccountParam(id, nickname, phone, dateOfBirth);
        }
    }
}
