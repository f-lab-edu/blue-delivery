package com.bluedelivery.application.shop.dto;

import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.domain.businesshour.BusinessHourType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessHoursTarget {
    private Long shopId;
    private BusinessHourType businessHourType;
    private Map<BusinessHourDay, BusinessHourParam> businessHours;
}
