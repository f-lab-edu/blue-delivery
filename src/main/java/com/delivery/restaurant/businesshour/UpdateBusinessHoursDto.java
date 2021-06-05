package com.delivery.restaurant.businesshour;

import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateBusinessHoursDto {
    
    public enum DayType {
        EVERYDAY, WEEKDAY, SATURDAY, SUNDAY;
    }
    
    public enum BusinessHourType {
        EVERY_SAME_TIME, WEEKDAY_SAT_SUNDAY
    }
    
    @NotNull
    private BusinessHourType businessHourType;
    @Size(min = 1, max = 3)
    private Map<DayType, BusinessHourRequestParam> businessHours;
    
    public UpdateBusinessHoursDto() {
    }
    
    public UpdateBusinessHoursDto(BusinessHourType businessHourType,
                                  Map<DayType, BusinessHourRequestParam> businessHours) {
        this.businessHourType = businessHourType;
        this.businessHours = businessHours;
    }
    
    public BusinessHourType getBusinessHourType() {
        return businessHourType;
    }
    
    public void setBusinessHourType(BusinessHourType businessHourType) {
        this.businessHourType = businessHourType;
    }
    
    public Map<DayType, BusinessHourRequestParam> getBusinessHours() {
        return businessHours;
    }
    
    public void setBusinessHours(Map<DayType, BusinessHourRequestParam> businessHours) {
        this.businessHours = businessHours;
    }
}
