package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;
import static java.time.DayOfWeek.*;

import java.time.DayOfWeek;

public class WeekdayWeekendBusinessHourCondition implements BusinessHourCondition {
    
    @Override
    public boolean isSatisfied(BusinessHourType type, BusinessHourRequestParams params) {
        if (params.isWeekdaySatSunday(type)) {
            return true;
        }
        return false;
    }
    
    @Override
    public BusinessHourPolicy returnBusinessHourPolicy(Long restId, BusinessHourRequestParams params) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam weekday = params.getParamByDayType(DayType.WEEKDAY);
        BusinessHourRequestParam sat = params.getParamByDayType(DayType.SATURDAY);
        BusinessHourRequestParam sun = params.getParamByDayType(DayType.SUNDAY);
        
        for (DayOfWeek day : values()) {
            if (day.compareTo(SATURDAY) >= 0) {
                break;
            }
            policy.update(weekday.toEntity(day));
        }
        policy.update(sat.toEntity(SATURDAY));
        policy.update(sun.toEntity(SUNDAY));
        return policy;
    }
}
