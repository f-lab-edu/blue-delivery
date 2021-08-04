package com.bluedelivery.application.shop.businesshour;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.domain.shop.BusinessHour;

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
    public List<BusinessHour> mapToDayOfWeek(Map<BusinessHourDay, BusinessHourParam> hours) {
        List<BusinessHour> bhs = new ArrayList<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            bhs.add(hours.get(BusinessHourDay.EVERY_DAY).toEntity(day));
        }
        return bhs;
    }
    
}
