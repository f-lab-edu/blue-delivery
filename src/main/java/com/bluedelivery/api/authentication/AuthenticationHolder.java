package com.bluedelivery.api.authentication;

import com.bluedelivery.domain.authentication.Authentication;

public class AuthenticationHolder {
    private static ThreadLocal<Authentication> authentication = new ThreadLocal<>();
    
    public static boolean hasAuthentication() {
        return authentication.get() != null;
    }
    
    public static Authentication getAuthentication() {
        return authentication.get();
    }
    
    public static void setAuthentication(Authentication auth) {
        authentication.set(auth);
    }
}
