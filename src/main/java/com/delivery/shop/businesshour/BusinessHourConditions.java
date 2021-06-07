package com.delivery.shop.businesshour;

import static com.delivery.shop.businesshour.UpdateBusinessHoursDto.*;

import java.util.ArrayList;
import java.util.List;

public class BusinessHourConditions {
    private static final List<BusinessHourCondition> conditions;
    
    static {
        conditions = new ArrayList<>();
        conditions.add(new EverydayBusinessHourCondition());
        conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public static BusinessHourPolicy makeBusinessHoursBy(Long shopId, UpdateBusinessHoursDto dto) {
        BusinessHourType type = dto.getBusinessHourType();
        BusinessHourRequestParams params = dto.getBusinessHours();
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, params)) {
                return condition.returnBusinessHourPolicy(shopId, params);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
