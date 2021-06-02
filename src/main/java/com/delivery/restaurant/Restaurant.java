package com.delivery.restaurant;

import com.delivery.restaurant.businesshour.BusinessHourPolicy;

public class Restaurant {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHour;
    private String introduce;
    private String phone;
    private String deliveryAreaGuide;
    
    public Restaurant() {
    
    }
    
    public void updateBusinessHour(BusinessHourPolicy bh) {
        this.businessHour = bh;
    }
    
    public void editIntroduce(String introduce) {
        this.introduce = introduce;
    }
    
    public void editPhoneNumber(String phone) {
        this.phone = phone;
    }
    
    public void editDeliveryAreaGuide(String guide) {
        this.deliveryAreaGuide = guide;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDeliveryAreaGuide() {
        return deliveryAreaGuide;
    }
    
    public BusinessHourPolicy getBusinessHour() {
        return businessHour;
    }
    
    public String getIntroduce() {
        return introduce;
    }
}
