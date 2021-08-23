package com.bluedelivery.domain.user;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bluedelivery.domain.address.Address;
import com.bluedelivery.domain.address.Addresses;

@Entity
public class User {
    
    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    private Addresses addresses;
    
    @Column(unique = true)
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
    
    public boolean validate(String password) {
        if (!this.password.equals(password)) {
            return false;
        }
        return true;
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
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public Address getMainAddress() {
        return addresses.getMainAddress();
    }
    
    public boolean designateAsMainAddress(Address address) {
        return addresses.designateAsMainAddress(address);
    }
    
    public boolean addAddress(Address address) {
        address.setUser(this);
        return addresses.addAddress(address);
    }
    
    public boolean removeAddress(Address address) {
        return addresses.removeAddress(address);
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
        return Objects.hash(id);
    }
}
