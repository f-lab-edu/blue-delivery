package com.delivery.restaurant.businesshour;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.delivery.utility.BusinessHourType;

public class UpdateBusinessHoursDto {
    
    @NotNull
    private BusinessHourType type;
    @Size(min = 1, max = 3)
    private List<BusinessHour> businessHours;
    
    public UpdateBusinessHoursDto() {
    }
    
    public UpdateBusinessHoursDto(BusinessHourType type, BusinessHour... businessHours) {
        this.type = type;
        this.businessHours = new ArrayList<>();
        Collections.addAll(this.businessHours, businessHours);
    }
    
    public BusinessHourType getType() {
        return type;
    }
    
    public void setType(BusinessHourType type) {
        this.type = type;
    }
    
    public List<BusinessHour> getBusinessHours() {
        return businessHours;
    }
    
    public void setBusinessHours(List<BusinessHour> businessHours) {
        this.businessHours = businessHours;
    }
}
