package com.delivery.user.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginParam {
    private final String email;
    private final String password;
    
    @Getter
    @RequiredArgsConstructor
    public static class UserLoginRequest {
        private final String email;
        private final String password;
        
        public UserLoginParam toParam() {
            return new UserLoginParam(email, password);
        }
    }
    
}


