package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;
import static java.time.DayOfWeek.*;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.function.Supplier;

public class WeekdayWeekendBusinessHourCondition implements BusinessHourCondition {
    
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<DayType, BusinessHour> bh) {
        if (type == BusinessHourType.WEEKDAY_SAT_SUNDAY && bh.size() == 3) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Map<DayType, BusinessHour> bhs) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHour weekday = matchBusinessHour(bhs, DayType.WEEKDAY);
        for (DayOfWeek day : values()) {
            policy.update(day, weekday);
        }
        policy.update(SATURDAY, matchBusinessHour(bhs, DayType.SATURDAY));
        policy.update(SUNDAY, matchBusinessHour(bhs, DayType.SUNDAY));
        return policy;
    }
    
    private BusinessHour matchBusinessHour(Map<DayType, BusinessHour> bhs, DayType dayType) {
        for (DayType dt : bhs.keySet()) {
            if (dt == dayType) {
                return bhs.get(dt);
            }
        }
        throw new IllegalArgumentException("proper type required");
    }
}
