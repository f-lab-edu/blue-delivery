package com.delivery.restaurant;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BusinessHours {
    protected Map<DayOfWeek, BusinessHour> businessHours;
    
    public BusinessHours() {
        this.businessHours = new LinkedHashMap<>();
    }
    
    public BusinessHour getBusinessHourOf(DayOfWeek day) {
        return this.businessHours.get(day);
    }
}
