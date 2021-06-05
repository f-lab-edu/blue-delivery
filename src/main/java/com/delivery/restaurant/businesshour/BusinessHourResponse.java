package com.delivery.restaurant.businesshour;

import java.util.List;

public class BusinessHourResponse {
    List<BusinessHour> businessHours;
    
    public BusinessHourResponse(List<BusinessHour> businessHours) {
        this.businessHours = businessHours;
    }
    
    public List<BusinessHour> getBusinessHours() {
        return businessHours;
    }
    
    public void setBusinessHours(List<BusinessHour> businessHours) {
        this.businessHours = businessHours;
    }
}
