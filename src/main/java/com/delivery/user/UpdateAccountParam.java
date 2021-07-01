package com.delivery.user;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.delivery.utility.RegexConstants;

public class UpdateAccountParam {
    private Long id;
    private String email;
    private String nickname;
    private String phone;
    private String password;
    private String address;
    
    public UpdateAccountParam(Long id, String email, String nickname, String phone, String password,
                              String address) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.address = address;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getAddress() {
        return address;
    }
    
    static class UpdateAccountRequest {
        
        private String email;
        
        @NotBlank(message = "닉네임은 공백이 될 수 없습니다.")
        private String nickname;
        
        @NotNull(message = "must not null")
        @Pattern(regexp = RegexConstants.PHONE,
                message = "01로 시작하는 10-11자리 숫자여야 합니다.")
        private String phone;
        
        @NotNull(message = "must not null")
        @Pattern(regexp = RegexConstants.PASSWORD,
                message = "알파벳, 숫자, 특수문자가 각 1개이상 포함된 8~20 글자여야 합니다.")
        private String password;
        
        private String address; // TODO
    
        public UpdateAccountRequest() {
        }
    
        public UpdateAccountRequest(String email, String nickname, String phone, String password) {
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.password = password;
        }
    
        public UpdateAccountRequest(String email, String nickname, String phone, String password, String address) {
            this.email = email;
            this.nickname = nickname;
            this.phone = phone;
            this.password = password;
            this.address = address;
        }
    
        public UpdateAccountParam toParam(Long id) {
            return new UpdateAccountParam(id, email, nickname, phone, password, address);
        }
    
        public String getEmail() {
            return email;
        }
    
        public String getNickname() {
            return nickname;
        }
    
        public String getPhone() {
            return phone;
        }
    
        public String getPassword() {
            return password;
        }
    
        public String getAddress() {
            return address;
        }
    }
}
