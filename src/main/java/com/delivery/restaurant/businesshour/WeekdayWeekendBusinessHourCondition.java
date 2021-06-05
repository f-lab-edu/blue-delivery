package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;
import static java.time.DayOfWeek.*;

import java.time.DayOfWeek;
import java.util.Map;

public class WeekdayWeekendBusinessHourCondition implements BusinessHourCondition {
    
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<DayType, BusinessHourRequestParam> bh) {
        if (type == BusinessHourType.WEEKDAY_SAT_SUNDAY && bh.size() == 3) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Map<DayType, BusinessHourRequestParam> bhs) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam weekday = matchBusinessHour(bhs, DayType.WEEKDAY);
        BusinessHourRequestParam sat = matchBusinessHour(bhs, DayType.SATURDAY);
        BusinessHourRequestParam sun = matchBusinessHour(bhs, DayType.SUNDAY);
        
        for (DayOfWeek day : values()) {
            if (day.compareTo(SATURDAY) >= 0) {
                break;
            }
            policy.update(day, new BusinessHour(weekday.getOpen(), weekday.getClose()));
        }
        policy.update(SATURDAY, new BusinessHour(sat.getOpen(), sat.getClose()));
        policy.update(SUNDAY, new BusinessHour(sun.getOpen(), sun.getClose()));
        return policy;
    }
    
    private BusinessHourRequestParam matchBusinessHour(Map<DayType, BusinessHourRequestParam> bhs, DayType dayType) {
        for (DayType dt : bhs.keySet()) {
            if (dt == dayType) {
                return bhs.get(dt);
            }
        }
        throw new IllegalArgumentException("proper type required");
    }
}
