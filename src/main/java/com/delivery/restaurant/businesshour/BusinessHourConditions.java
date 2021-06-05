package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BusinessHourConditions {
    private static List<BusinessHourCondition> conditions;
    
    static {
        conditions = new ArrayList<>();
        conditions.add(new EverydayBusinessHourCondition());
        conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public static BusinessHourPolicy makeBusinessHoursBy(Long restId, UpdateBusinessHoursDto dto) {
        BusinessHourType type = dto.getBusinessHourType();
        Map<DayType, BusinessHourRequestParam> bhs = dto.getBusinessHours();
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, bhs)) {
                return condition.returnBusinessHourPolicy(restId, bhs);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
