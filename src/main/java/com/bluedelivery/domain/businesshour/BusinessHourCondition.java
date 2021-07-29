package com.bluedelivery.domain.businesshour;

import java.time.DayOfWeek;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> params);
    
    Map<DayOfWeek, BusinessHourParam> mapToDayOfWeek(Map<BusinessHourDay, BusinessHourParam> params);
}
