package com.delivery.utility.address;

import static java.util.Objects.isNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "buildingManagementNumber")
    private BuildingInfo buildingInfo;
    private String detail;
    
    public Address() {
    
    }
    
    public Address(BuildingInfo buildingInfo, String detail) {
        this.buildingInfo = buildingInfo;
        this.detail = detail;
    }
    
    public String getFullRoadNameAddress() {
        if (isNull(buildingInfo)) {
            return "";
        }
        return new StringBuilder()
                .append(buildingInfo.getRoadNameAddress()).append(" ")
                .append(detail)
                .append(buildingInfo.getBuildingDetail()).append(")")
                .toString();
    }
}
