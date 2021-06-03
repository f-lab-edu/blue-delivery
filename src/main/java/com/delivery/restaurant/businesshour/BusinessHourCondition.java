package com.delivery.restaurant.businesshour;

import java.util.Map;


public interface BusinessHourCondition {
    
    boolean isSatisfied(UpdateBusinessHoursDto.BusinessHourType type,
                        Map<UpdateBusinessHoursDto.DayType, BusinessHour> bh);
    
    BusinessHourPolicy returnBusinessHourPolicy(Map<UpdateBusinessHoursDto.DayType, BusinessHour> bh);
}
