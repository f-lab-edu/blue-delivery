package com.bluedelivery.domain.businesshour;

import static com.bluedelivery.api.shop.dto.BusinessHourDay.*;

import java.time.DayOfWeek;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;

public class WeekdayWeekendBusinessHourCondition implements BusinessHourCondition {
    
    @Override
    public boolean isSatisfied(BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> hours) {
        if (type == BusinessHourType.WEEKDAY_SAT_SUNDAY
                && hours.size() == 3
                && hasRequiredDays(hours)) {
            return true;
        }
        return false;
    }
    
    @Override
    public Map<DayOfWeek, BusinessHourParam> mapToDayOfWeek(Map<BusinessHourDay, BusinessHourParam> hours) {
        Map<DayOfWeek, BusinessHourParam> map = new HashMap<>();
        BusinessHourParam weekday = hours.get(WEEKDAY);
        BusinessHourParam saturday = hours.get(SATURDAY);
        BusinessHourParam sunday = hours.get(SUNDAY);
        
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.compareTo(DayOfWeek.SATURDAY) >= 0) {
                break;
            }
            map.put(day, weekday);
        }
        map.put(DayOfWeek.SATURDAY, saturday);
        map.put(DayOfWeek.SUNDAY, sunday);
    
        return map;
    }
    
    private boolean hasRequiredDays(Map<BusinessHourDay, BusinessHourParam> hours) {
        return hours.containsKey(WEEKDAY)
                && hours.containsKey(SATURDAY)
                && hours.containsKey(SUNDAY);
    }
}
