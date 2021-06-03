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
    
    public static BusinessHourPolicy makeBusinessHoursBy(BusinessHourType type,
                                                         Map<DayType, BusinessHour> bh) {
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, bh)) {
                return condition.returnBusinessHourPolicy(bh);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
