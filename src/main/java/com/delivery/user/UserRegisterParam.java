package com.delivery.user;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRegisterParam {
    private final String email;
    private final String nickname;
    private final String phone;
    private final String password;
    private final LocalDate dateOfBirth;
    private final String address;
    
    public User toEntity() {
        return new User(
                this.email,
                this.nickname,
                this.phone,
                this.password,
                this.dateOfBirth,
                this.address
        );
    }
    
    @Getter
    @RequiredArgsConstructor
    static class UserRegisterRequest {
        
        @NotBlank
        @Email(message = "이메일 형식이어야 합니다.")
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
    
        @NotBlank
        @Pattern(regexp = RegexConstants.PASSWORD,
                message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
        private final String confirmedPassword;
        
        @NotNull
        @Past(message = "올바르지 않은 생년월일 입니다.")
        private final LocalDate dateOfBirth;
        
        @NotNull
        private final String address; // TODO
        
        public UserRegisterParam toParam() {
            return new UserRegisterParam(
                    this.email,
                    this.nickname,
                    this.phone,
                    this.password,
                    this.dateOfBirth,
                    this.address
            );
        }
    }
}
