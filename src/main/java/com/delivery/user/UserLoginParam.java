package com.delivery.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserLoginParam {
    private final String email;
    private final String password;
    
    @Getter
    @RequiredArgsConstructor
    static class UserLoginRequest {
        private final String email;
        private final String password;
        
        public UserLoginParam toParam() {
            return new UserLoginParam(email, password);
        }
    }
    
}


