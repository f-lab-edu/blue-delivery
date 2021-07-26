package com.bluedelivery.shop.businesshour;

import com.bluedelivery.shop.businesshour.UpdateBusinessHoursDto.BusinessHourType;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params);
    
    BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params);
}
