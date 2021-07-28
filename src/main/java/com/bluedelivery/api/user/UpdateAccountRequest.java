package com.bluedelivery.api.user;


import java.time.LocalDate;

import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.bluedelivery.application.user.UpdateAccountTarget;
import com.bluedelivery.common.RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAccountRequest {
    
    private String nickname;
    
    @Pattern(regexp = RegexConstants.PHONE,
            message = "01로 시작하는 10-11자리 숫자여야 합니다.")
    private String phone;
    @Past
    private LocalDate dateOfBirth;
    
    public UpdateAccountTarget toParam(Long id) {
        return new UpdateAccountTarget(id, nickname, phone, dateOfBirth);
    }
}
