package com.delivery.utility.address;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "addressJurisdictionEupMyonDongCode")
    private CityToDong cityToDong;
    @OneToOne
    @JoinColumn(name = "buildingManagementNumber")
    private BuildingInfo buildingInfo;
    private String detail;
}
