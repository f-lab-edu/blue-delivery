package com.bluedelivery.domain.businesshour;

import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> hours) {
        if (type == BusinessHourType.EVERY_SAME_TIME
                && hours.size() == 1
                && hours.containsKey(BusinessHourDay.EVERY_DAY)) {
            return true;
        }
        return false;
    }
    
    @Override
    public Map<DayOfWeek, BusinessHourParam> mapToDayOfWeek(Map<BusinessHourDay, BusinessHourParam> hours) {
        Map<DayOfWeek, BusinessHourParam> map = new HashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            map.put(day, hours.get(BusinessHourDay.EVERY_DAY));
        }
        return map;
    }
    
}
