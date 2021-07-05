package com.delivery.user;

public class Authentication {
    public static String KEY = "auth"; // session에 key로 스트링을 넣을 때 사용
    private String email;
    private String nickname;
    private String phone;
    
    public Authentication() {
    }
    
    public Authentication(String email, String nickname, String phone) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
    }
}
