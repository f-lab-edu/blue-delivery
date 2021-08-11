package com.bluedelivery.domain.shop;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.bluedelivery.domain.address.CityToDong;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DeliveryArea {
    @Column(name = "TOWN_CODE")
    private String townCode;
    
    @Column(name = "TOWN_NAME")
    private String townName;
    
    public static DeliveryArea of(CityToDong town) {
        return new DeliveryArea(
                town.getAddressJurisdictionEupMyonDongCode(),
                town.getEupMyonDongName());
    }
}
