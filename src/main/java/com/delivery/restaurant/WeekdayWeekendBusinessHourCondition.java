package com.delivery.restaurant;

import java.util.List;

import com.delivery.utility.BusinessHourType;

public class WeekdayWeekendBusinessHourCondition implements BusinessHourCondition {
    
    
    @Override
    public boolean isSatisfied(BusinessHourType type, List<BusinessHour> bh) {
        if (type == BusinessHourType.WEEKDAY_SAT_SUNDAY && bh.size() == 3) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHours matchBusinessHours(List<BusinessHour> bh) {
        return new WeekdayWeekendBusinessHour(bh);
    }
}
