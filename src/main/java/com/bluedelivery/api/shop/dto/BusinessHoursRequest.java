package com.bluedelivery.api.shop.dto;

import java.util.Map;

import javax.validation.constraints.NotNull;

import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.application.shop.dto.BusinessHoursTarget;
import com.bluedelivery.domain.businesshour.BusinessHourType;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class BusinessHoursRequest {
    
    @NotNull
    private BusinessHourType businessHourType;
    @NotNull
    private Map<BusinessHourDay, BusinessHourParam> businessHours;
    
    public BusinessHoursTarget toTarget(Long shopId) {
        return new BusinessHoursTarget(shopId, businessHourType, businessHours);
    }
}
