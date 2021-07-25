package com.delivery.shop.businesshour;

import static com.delivery.shop.businesshour.UpdateBusinessHoursDto.*;

import java.util.Collections;
import java.util.Map;

public class BusinessHourRequestParams {
    private Map<DayType, BusinessHourRequestParam> params;
    
    public BusinessHourRequestParams() {
    }
    
    public BusinessHourRequestParams(Map<DayType, BusinessHourRequestParam> params) {
        this.params = params;
    }
    
    public boolean isEveryDayHours(BusinessHourType type) {
        return type == BusinessHourType.EVERY_SAME_TIME
                && params.size() == 1
                && params.containsKey(DayType.EVERYDAY);
    }
    
    public boolean isWeekdaySatSunday(BusinessHourType type) {
        return type == BusinessHourType.WEEKDAY_SAT_SUNDAY
                && params.size() == 3
                && params.containsKey(DayType.WEEKDAY)
                && params.containsKey(DayType.SATURDAY)
                && params.containsKey(DayType.SUNDAY);
    }
    
    public BusinessHourRequestParam retrieveParamByDayType(DayType dayType) {
        return params.get(dayType);
    }
    
    public Map<DayType, BusinessHourRequestParam> getParams() {
        return Collections.unmodifiableMap(params);
    }
    
}
