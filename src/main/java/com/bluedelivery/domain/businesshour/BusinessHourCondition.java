package com.bluedelivery.domain.businesshour;

import com.bluedelivery.api.shop.UpdateBusinessHoursDto.BusinessHourType;

public interface BusinessHourCondition {
    
    boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params);
    
    BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params);
}
