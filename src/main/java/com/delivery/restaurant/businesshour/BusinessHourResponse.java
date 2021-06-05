package com.delivery.restaurant.businesshour;

import java.time.DayOfWeek;

public class BusinessHourResponse {
    DayOfWeek day;
    BusinessHour businessHour;
    
    public DayOfWeek getDay() {
        return day;
    }
    
    public BusinessHour getBusinessHour() {
        return businessHour;
    }
}
