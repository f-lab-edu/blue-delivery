package com.delivery.restaurant.businesshour;

import com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.BusinessHourType;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params);
    
    BusinessHourPolicy returnBusinessHourPolicy(Long restId, BusinessHourRequestParams params);
}
