package com.delivery.restaurant.businesshour;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.delivery.utility.BusinessHourType;

@Component
public class BusinessHourConditions {
    private List<BusinessHourCondition> conditions;
    
    public BusinessHourConditions() {
        this.conditions = new ArrayList<>();
        this.conditions.add(new EverydayBusinessHourCondition());
        this.conditions.add(new WeekdayWeekendBusinessHourCondition());
    }
    
    public BusinessHourPolicy makeBusinessHoursBy(BusinessHourType type, List<BusinessHour> bh) {
        for (BusinessHourCondition condition : conditions) {
            if (condition.isSatisfied(type, bh)) {
                return condition.returnBusinessHourPolicy(bh);
            }
        }
        throw new IllegalArgumentException("wrong values for business hour");
    }
    
}
