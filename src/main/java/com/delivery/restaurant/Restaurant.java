package com.delivery.restaurant;

public class Restaurant {
    private String name;
    private BusinessHours businessHour;
    
    public Restaurant() {
    }
    
    public void updateBusinessHour(BusinessHours bh) {
        this.businessHour = bh;
    }
    
}
