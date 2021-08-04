package com.bluedelivery.application.shop.businesshour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.application.shop.dto.BusinessHoursTarget;
import com.bluedelivery.domain.shop.BusinessHour;

public class DayOfWeekMapper {
    private static final List<BusinessHourCondition> conditions = new ArrayList<>();
    
    static {
        conditions.add(new EverydayBusinessHourCondition());
        conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public static List<BusinessHour> map(BusinessHoursTarget target) {
        BusinessHourType type = target.getBusinessHourType();
        Map<BusinessHourDay, BusinessHourParam> hours = target.getBusinessHours();
        
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, hours)) {
                List<BusinessHour> list = condition.mapToDayOfWeek(hours);
                Collections.sort(list);
                return list;
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
