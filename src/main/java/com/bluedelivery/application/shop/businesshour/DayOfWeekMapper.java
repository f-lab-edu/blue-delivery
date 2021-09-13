package com.bluedelivery.application.shop.businesshour;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.bluedelivery.api.shop.dto.BusinessHourDay;
import com.bluedelivery.application.shop.dto.BusinessHourParam;
import com.bluedelivery.domain.shop.BusinessHour;

@Component
public class DayOfWeekMapper {
    
    private final List<BusinessHourCondition> conditions;
    
    public DayOfWeekMapper(List<BusinessHourCondition> providers) {
        this.conditions = providers;
    }
    
    public List<BusinessHour> map(BusinessHourType type, Map<BusinessHourDay, BusinessHourParam> hours) {
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
