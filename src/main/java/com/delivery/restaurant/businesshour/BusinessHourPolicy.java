package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

public class BusinessHourPolicy {
    protected Long id;
    protected Map<DayOfWeek, BusinessHour> businessHours;
    
    public BusinessHourPolicy() {
        this.businessHours = new LinkedHashMap<>();
    }
    
    public void setup(Map<DayOfWeek, BusinessHour> bhs) {
        this.businessHours = bhs;
    }
    
    public void update(DayOfWeek day, BusinessHour businessHour) {
        businessHours.put(day, businessHour);
    }
    
    public BusinessHour getBusinessHourOf(DayOfWeek day) {
        return this.businessHours.get(day);
    }
    
    public Map<DayOfWeek, BusinessHour> getBusinessHours() {
        return businessHours;
    }
}
