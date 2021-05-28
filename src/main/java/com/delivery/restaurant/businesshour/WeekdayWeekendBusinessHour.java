package com.delivery.restaurant.businesshour;

import static java.time.DayOfWeek.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.function.Supplier;

import com.delivery.utility.BusinessHourType;
import com.delivery.utility.DayType;

public class WeekdayWeekendBusinessHour extends BusinessHours {
    private Supplier<IllegalArgumentException> ex = () -> new IllegalArgumentException("proper type required");
    
    public WeekdayWeekendBusinessHour(List<BusinessHour> bh) {
        super.businessHourType = BusinessHourType.WEEKDAY_SAT_SUNDAY;
        BusinessHour weekday = matchBusinessHour(bh, DayType.WEEKDAY);
        for (DayOfWeek day : values()) {
            super.businessHours.put(day, weekday);
        }
        super.businessHours.put(SATURDAY, matchBusinessHour(bh, DayType.SATURDAY));
        super.businessHours.put(SUNDAY, matchBusinessHour(bh, DayType.SUNDAY));
    }
    
    private BusinessHour matchBusinessHour(List<BusinessHour> bh, DayType dayType) {
        return bh.stream()
                .filter(x -> x.getDayType() == dayType)
                .findFirst()
                .orElseThrow(ex);
    }
}
