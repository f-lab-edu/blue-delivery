package com.delivery.restaurant.businesshour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.delivery.utility.BusinessHourType;

@Component
public class BusinessHoursConditions {
    private List<BusinessHourCondition> conditions;
    
    public BusinessHoursConditions() {
        this.conditions = new ArrayList<>();
        this.conditions.add(new EveryDayBusinessHourCondition());
        this.conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public BusinessHours makeBusinessHoursBy(BusinessHourType type, List<BusinessHour> bh) {
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, bh)) {
                return condition.matchBusinessHours(bh);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
