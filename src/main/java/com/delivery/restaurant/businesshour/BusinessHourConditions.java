package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.util.ArrayList;
import java.util.List;

public class BusinessHourConditions {
    private static final List<BusinessHourCondition> conditions;
    
    static {
        conditions = new ArrayList<>();
        conditions.add(new EverydayBusinessHourCondition());
        conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public static BusinessHourPolicy makeBusinessHoursBy(Long restId, UpdateBusinessHoursDto dto) {
        BusinessHourType type = dto.getBusinessHourType();
        BusinessHourRequestParams params = dto.getBusinessHours();
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, params)) {
                return condition.returnBusinessHourPolicy(restId, params);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
