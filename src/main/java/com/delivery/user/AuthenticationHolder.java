package com.delivery.user;

public class AuthenticationHolder {
    private static ThreadLocal<Authentication> local = new ThreadLocal<>();
    
    public static boolean hasAuthentication() {
        return local.get() != null;
    }
    
    public static Authentication getAuthentication() {
        return local.get();
    }
    
    public static void setAuthentication(Authentication auth) {
        local.set(auth);
    }
    
    public static void reset() {
        local.remove();
    }
}
