package com.delivery.restaurant.businesshour;

import java.util.List;

import com.delivery.utility.BusinessHourType;

public class EverydayBusinessHourCondition implements BusinessHourCondition {
    @Override
    public boolean isSatisfied(BusinessHourType type, List<BusinessHour> bh) {
        if (type == BusinessHourType.EVERYDAY && bh.size() == 1) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(List<BusinessHour> bh) {
        return new EverydayBusinessHourPolicy(bh);
    }
    
}
