package com.bluedelivery.application.shop.businesshour;

import java.util.List;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.domain.shop.BusinessHour;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> params);
    
    List<BusinessHour> mapToDayOfWeek(Map<BusinessHourDay, BusinessHourParam> params);
}
