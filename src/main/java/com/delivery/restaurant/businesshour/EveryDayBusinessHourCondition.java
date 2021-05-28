package com.delivery.restaurant.businesshour;

import java.util.List;

import com.delivery.utility.BusinessHourType;

public class EveryDayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType type, List<BusinessHour> bh) {
        if (type == BusinessHourType.EVERYDAY && bh.size() == 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHours matchBusinessHours(List<BusinessHour> bh) {
        return new EveryDayBusinessHour(bh);
    }
    
}
