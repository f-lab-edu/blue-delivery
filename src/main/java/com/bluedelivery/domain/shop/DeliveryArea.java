package com.bluedelivery.domain.shop;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bluedelivery.domain.address.CityToDong;

@Embeddable
public class DeliveryArea {
    @Column(name = "TOWN_CODE")
    private String townCode;
    
    @Column(name = "TOWN_NAME")
    private String townName;
    
    public DeliveryArea() {
    }
    
    public DeliveryArea(String townCode, String townName) {
        this.townCode = townCode;
        this.townName = townName;
    }
    
    public static DeliveryArea of(CityToDong town) {
        return new DeliveryArea(
                town.getAddressJurisdictionEupMyonDongCode(),
                town.getEupMyonDongName());
    }
    
    public String getTownCode() {
        return townCode;
    }
    
    public String getTownName() {
        return townName;
    }
}
