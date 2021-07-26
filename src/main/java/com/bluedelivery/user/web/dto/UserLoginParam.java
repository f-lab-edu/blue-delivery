package com.bluedelivery.user.web.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode
@Getter
@RequiredArgsConstructor
public class UserLoginParam {
    private final String email;
    private final String password;
    
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserLoginRequest {
        private String email;
        private String password;
        
        public UserLoginParam toParam() {
            return new UserLoginParam(email, password);
        }
    }
    
}


