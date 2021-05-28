package com.delivery.restaurant;

import java.time.DayOfWeek;
import java.util.List;


public class EveryDayBusinessHour extends BusinessHours {
    
    public EveryDayBusinessHour(List<BusinessHour> businessHours) {
        BusinessHour businessHour = businessHours.get(0);
        for (DayOfWeek day : DayOfWeek.values()) {
            super.businessHours.put(day, businessHour);
        }
    }
}
