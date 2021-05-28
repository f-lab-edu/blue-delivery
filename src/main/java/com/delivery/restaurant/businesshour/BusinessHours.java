package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.delivery.utility.BusinessHourType;

public abstract class BusinessHours {
    protected BusinessHourType businessHourType;
    protected Map<DayOfWeek, BusinessHour> businessHours;
    
    public BusinessHours() {
        this.businessHours = new LinkedHashMap<>();
    }
    
    public BusinessHour getBusinessHourOf(DayOfWeek day) {
        return this.businessHours.get(day);
    }
    
    public Set<BusinessHour> getBusinessHoursByDayType() {
        return businessHours.values().stream().collect(Collectors.toSet());
    }
    
    public BusinessHourType getBusinessHourType() {
        return businessHourType;
    }
}
