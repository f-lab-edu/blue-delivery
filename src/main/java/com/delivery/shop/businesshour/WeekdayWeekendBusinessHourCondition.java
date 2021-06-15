package com.delivery.shop.businesshour;

import static com.delivery.shop.businesshour.UpdateBusinessHoursDto.*;
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
    public BusinessHourPolicy returnBusinessHourPolicy(Long shopId, BusinessHourRequestParams params) {
        BusinessHourPolicy policy = new BusinessHourPolicy();
        BusinessHourRequestParam weekday = params.retreiveParamByDayType(DayType.WEEKDAY);
        BusinessHourRequestParam sat = params.retreiveParamByDayType(DayType.SATURDAY);
        BusinessHourRequestParam sun = params.retreiveParamByDayType(DayType.SUNDAY);
        
        for (DayOfWeek day : values()) {
            if (day.compareTo(SATURDAY) >= 0) {
                break;
            }
            policy.update(weekday.toEntity(shopId, day));
        }
        policy.update(sat.toEntity(shopId, SATURDAY));
        policy.update(sun.toEntity(shopId, SUNDAY));
        return policy;
    }
}
