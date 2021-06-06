package com.delivery.restaurant.businesshour;

import static com.delivery.restaurant.businesshour.UpdateBusinessHoursDto.*;

import java.util.Collections;
import java.util.List;

public class BusinessHourRequestParams {
    private List<BusinessHourRequestParam> params;
    
    public BusinessHourRequestParams() {
    }
    
    public BusinessHourRequestParams(List<BusinessHourRequestParam> params) {
        this.params = params;
    }
    
    public boolean isEveryDayHours(BusinessHourType type) {
        return type == BusinessHourType.EVERY_SAME_TIME
                && params.size() == 1
                && params.stream().anyMatch(x -> x.is(DayType.EVERYDAY));
    }
    
    public boolean isWeekdaySatSunday(BusinessHourType type) {
        return type == BusinessHourType.WEEKDAY_SAT_SUNDAY
                && params.size() == 3
                && params.stream().anyMatch(x -> x.is(DayType.WEEKDAY))
                && params.stream().anyMatch(x -> x.is(DayType.SATURDAY))
                && params.stream().anyMatch(x -> x.is(DayType.SUNDAY));
    }
    
    public BusinessHourRequestParam getParamByDayType(DayType dayType) {
        return params.stream()
                .filter(x -> x.getDayType() == dayType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("business hours parameter does not exist"));
    }
    
    public List<BusinessHourRequestParam> getParams() {
        return Collections.unmodifiableList(params);
    }
    
}
