package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;
import java.util.List;

import com.delivery.utility.BusinessHourType;


public class EverydayBusinessHourPolicy extends BusinessHourPolicy {
    public EverydayBusinessHourPolicy(List<BusinessHour> businessHours) {
        super.businessHourType = BusinessHourType.EVERYDAY;
        BusinessHour businessHour = businessHours.get(0);
        for (DayOfWeek day : DayOfWeek.values()) {
            super.businessHours.put(day, businessHour);
        }
    }
}
