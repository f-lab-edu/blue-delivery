package com.bluedelivery.user.web.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.bluedelivery.user.domain.User;
import com.bluedelivery.utility.RegexConstants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterParam {
    private final String email;
    private final String nickname;
    private final String phone;
    private final String password;
    private final LocalDate dateOfBirth;
    
    public User toEntity() {
        return new User(email, nickname, phone, password, dateOfBirth);
    }
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserRegisterRequest {
        
        @NotBlank
        @Email
        private String email;
        
        @NotBlank
        private String nickname;
        
        @NotBlank
        @Pattern(regexp = RegexConstants.PHONE, message = "01로 시작하는 10-11자리 숫자여야 합니다.")
        private String phone;
        
        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD, message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
        private String password;
        
        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD, message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
        private String confirmedPassword;
        
        @NotNull
        @Past(message = "올바르지 않은 생년월일 입니다.")
        private LocalDate dateOfBirth;
        
        public UserRegisterParam toParam() {
            return new UserRegisterParam(email, nickname, phone, password, dateOfBirth);
        }
    }
}
