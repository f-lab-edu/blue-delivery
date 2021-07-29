package com.bluedelivery.domain.businesshour;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;

public class DayOfWeekMapper {
    private static final List<BusinessHourCondition> conditions = new ArrayList<>();
    
    static {
        conditions.add(new EverydayBusinessHourCondition());
        conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public static Map<DayOfWeek, BusinessHourParam> map(
            BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> hours) {
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, hours)) {
                return condition.mapToDayOfWeek(hours);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
