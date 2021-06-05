package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.time.DayOfWeek;
import java.util.Map;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<DayType, BusinessHourRequestParam> bhs) {
        if (type == BusinessHourType.EVERY_SAME_TIME && bhs.size() == 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Long restId, Map<DayType, BusinessHourRequestParam> bhs) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam businessHour = bhs.get(DayType.EVERYDAY);
        for (DayOfWeek day : DayOfWeek.values()) {
            policy.update(day, new BusinessHour(restId, businessHour.getOpen(), businessHour.getClose()));
        }
        return policy;
    }
    
}
