package com.delivery.restaurant.businesshour;

import java.util.Map;

public interface BusinessHourCondition {
    
    boolean isSatisfied(UpdateBusinessHoursDto.BusinessHourType type,
                        Map<UpdateBusinessHoursDto.DayType, BusinessHourRequestParam> bh);
    
    BusinessHourPolicy returnBusinessHourPolicy(Long restId,
                                                Map<UpdateBusinessHoursDto.DayType, BusinessHourRequestParam> bh);
}
