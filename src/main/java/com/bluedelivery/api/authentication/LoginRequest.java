package com.bluedelivery.api.authentication;

import com.bluedelivery.application.authentication.LoginTarget;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    
    private String email;
    private String password;
    
    public LoginTarget toParam() {
        return new LoginTarget(email, password);
    }
}
