package com.delivery.restaurant;

import com.delivery.restaurant.businesshour.BusinessHourPolicy;

public class Restaurant {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHour;
    
    public Restaurant() {
    
    }
    
    public void updateBusinessHour(BusinessHourPolicy bh) {
        this.businessHour = bh;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public BusinessHourPolicy getBusinessHour() {
        return businessHour;
    }
}
