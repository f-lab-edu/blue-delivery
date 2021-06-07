package com.delivery.shop.businesshour;

import com.delivery.shop.businesshour.UpdateBusinessHoursDto.BusinessHourType;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params);
    
    BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params);
}
