package com.delivery.restaurant;

import com.delivery.restaurant.businesshour.BusinessHours;

public class Restaurant {
    private Long id;
    private String name;
    private BusinessHours businessHour;
    
    public Restaurant() {
    
    }
    
    public void updateBusinessHour(BusinessHours bh) {
        this.businessHour = bh;
    }
    
    public void rename(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public BusinessHours getBusinessHour() {
        return businessHour;
    }
}
