package com.delivery.restaurant;

import com.delivery.restaurant.businesshour.BusinessHourPolicy;

public class Restaurant {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHour;
    private String introduce;
    
    public Restaurant() {
    
    }
    
    public void updateBusinessHour(BusinessHourPolicy bh) {
        this.businessHour = bh;
    }
    
    public void editIntroduce(String introduce) {
        this.introduce = introduce;
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
    
    public String getIntroduce() {
        return introduce;
    }
}
