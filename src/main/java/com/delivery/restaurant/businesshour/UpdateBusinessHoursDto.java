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
    private Map<DayType, BusinessHourRequestParam> businessHoursDto;
    
    public UpdateBusinessHoursDto() {
    }
    
    public UpdateBusinessHoursDto(BusinessHourType businessHourType,
                                  Map<DayType, BusinessHourRequestParam> businessHoursDto) {
        this.businessHourType = businessHourType;
        this.businessHoursDto = businessHoursDto;
    }
    
    public BusinessHourType getBusinessHourType() {
        return businessHourType;
    }
    
    public void setBusinessHourType(BusinessHourType businessHourType) {
        this.businessHourType = businessHourType;
    }
    
    public Map<DayType, BusinessHourRequestParam> getBusinessHoursDto() {
        return businessHoursDto;
    }
    
    public void setBusinessHoursDto(Map<DayType, BusinessHourRequestParam> businessHoursDto) {
        this.businessHoursDto = businessHoursDto;
    }
}
