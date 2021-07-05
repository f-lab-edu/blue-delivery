package com.delivery.user;

import java.time.LocalDate;
import java.util.Objects;

import com.delivery.exception.ApiException;
import com.delivery.exception.ExceptionEnum;

public class User {
    
    private Long id;
    private String email;
    private String nickname;
    private String phone;
    private String password;
    private LocalDate dateOfBirth;
    private String address; // TODO 가게 조회시에 사용하기 위해 String 말고 다른 클래스 객체가 필요할 것 같음
    
    public User() {
    }
    
    public User(String email, String nickname, String phone, String password, LocalDate dateOfBirth, String address) {
        this.email = email;
        this.nickname = nickname;
        this.phone = phone.replaceAll("-", "");
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
    
    public User(Long id, String email, String nickname, String phone, String password,
                LocalDate dateOfBirth, String address) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }
    
    public void validate(String email, String password) {
        if (!this.email.equals(email) || !this.password.equals(password)) {
            throw new ApiException(ExceptionEnum.USER_NOT_VALIDATED);
        }
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
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void changePhone(String phone) {
        this.phone = phone;
    }
    
    public void changeAddress(String address) {
        this.address = address;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(id, user.id)
                && Objects.equals(email, user.email)
                && Objects.equals(nickname, user.nickname)
                && Objects.equals(phone, user.phone)
                && Objects.equals(password, user.password)
                && Objects.equals(dateOfBirth, user.dateOfBirth)
                && Objects.equals(address, user.address);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, email, nickname, phone, password, dateOfBirth, address);
    }
    
}
