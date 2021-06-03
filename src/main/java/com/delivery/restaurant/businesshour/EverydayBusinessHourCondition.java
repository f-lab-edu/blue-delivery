package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.time.DayOfWeek;
import java.util.Map;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<DayType, BusinessHour> bhs) {
        if (type == BusinessHourType.EVERY_SAME_TIME && bhs.size() == 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Map<DayType, BusinessHour> bhs) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHour businessHour = bhs.get(DayType.EVERYDAY);
        for (DayOfWeek day : DayOfWeek.values()) {
            policy.update(day, businessHour);
        }
        return policy;
    }
    
}
