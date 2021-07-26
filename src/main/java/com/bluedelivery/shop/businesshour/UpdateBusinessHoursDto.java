package com.bluedelivery.shop.businesshour;

import javax.validation.constraints.NotNull;

public class UpdateBusinessHoursDto {
    
    public enum DayType {
        EVERYDAY, WEEKDAY, SATURDAY, SUNDAY;
    }
    
    public enum BusinessHourType {
        EVERY_SAME_TIME, WEEKDAY_SAT_SUNDAY
    }
    
    @NotNull
    private BusinessHourType businessHourType;
    @NotNull
    private BusinessHourRequestParams businessHours;
    
    public UpdateBusinessHoursDto() {
    }
    
    public UpdateBusinessHoursDto(BusinessHourType businessHourType,
                                  BusinessHourRequestParams businessHours) {
        this.businessHourType = businessHourType;
        this.businessHours = businessHours;
    }
    
    public BusinessHourType getBusinessHourType() {
        return businessHourType;
    }
    
    public BusinessHourRequestParams getBusinessHours() {
        return businessHours;
    }
}
