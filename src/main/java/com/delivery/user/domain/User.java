package com.delivery.user.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.delivery.response.ApiException;
import com.delivery.response.ErrorCode;
import com.delivery.utility.address.domain.Address;
import com.delivery.utility.address.domain.Addresses;

@Entity
public class User {
    
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    private Addresses addresses;
    
    private String email;
    private String nickname;
    private String phone;
    private String password;
    private LocalDate dateOfBirth;
    
    
    public User() {
    }
    
    public User(String email, String nickname, String phone, String password, LocalDate dateOfBirth) {
        this.addresses = new Addresses();
        this.email = email;
        this.nickname = nickname;
        this.phone = phone.replaceAll("-", "");
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }
    
    public void validate(String email, String password) {
        if (!this.email.equals(email) || !this.password.equals(password)) {
            throw new ApiException(ErrorCode.USER_NOT_VALIDATED);
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
    
    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
    
    public void changePhone(String phone) {
        this.phone = phone;
    }
    
    public Addresses getAddresses() {
        return addresses;
    }
    
    public void designateAsMainAddress(Address address) {
        addresses.designateAsMainAddress(this, address);
    }
    
    public void addAddress(Address address) {
        addresses.addAddress(this, address);
    }
    
    public void removeAddress(Address address) {
        addresses.removeAddress(address);
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
        return Objects.equals(id, user.id) && Objects.equals(addresses, user.addresses)
                && Objects.equals(email, user.email) && Objects.equals(nickname, user.nickname)
                && Objects.equals(phone, user.phone) && Objects.equals(password, user.password)
                && Objects.equals(dateOfBirth, user.dateOfBirth);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, addresses, email, nickname, phone, password, dateOfBirth);
    }
}
