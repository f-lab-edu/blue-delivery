package com.bluedelivery.utility.address;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.bluedelivery.user.domain.User;

@Entity
public class Address {
    
    @Id
    @Column(name = "ADDRESS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private User user;
    
    @OneToOne
    @JoinColumn(name = "BUILDING_MANAGEMENT_NUMBER")
    private BuildingInfo buildingInfo;
    private String detail;
    
    
    public Address() {
    
    }
    
    public Address(BuildingInfo buildingInfo, String detail) {
        this.buildingInfo = buildingInfo;
        this.detail = detail;
    }
    
    public Long getId() {
        return id;
    }
    
    public User getUser() {
        return user;
    }
    
    public BuildingInfo getBuildingInfo() {
        return buildingInfo;
    }
    
    public String getDetail() {
        return detail;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address address = (Address) obj;
        return Objects.equals(user, address.user)
                && Objects.equals(buildingInfo, address.buildingInfo)
                && Objects.equals(detail, address.detail);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(user, buildingInfo, detail);
    }
}
