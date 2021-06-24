package com.delivery.shop.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.category.Categories;
import com.delivery.shop.closingday.ClosingDayPolicies;
import com.delivery.shop.closingday.ClosingDayPolicy;
import com.delivery.shop.suspension.Suspension;

public class Shop {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHourPolicy;
    private String introduce;
    private String phone;
    private String deliveryAreaGuide;
    private Categories categories;
    private ClosingDayPolicies closingDayPolicies;
    private boolean exposed;
    private Suspension suspension;
    
    public Shop() {
        closingDayPolicies = new ClosingDayPolicies();
        categories = new Categories();
        suspension = new Suspension();
    }
    
    // 테스트코드에서 사용
    public Shop(Long id, String name, BusinessHourPolicy businessHourPolicy, ClosingDayPolicies closingDayPolicies) {
        this.id = id;
        this.name = name;
        this.businessHourPolicy = businessHourPolicy;
        this.closingDayPolicies = closingDayPolicies;
        this.suspension = new Suspension();
    }
    
    public void updateBusinessHour(BusinessHourPolicy bh) {
        this.businessHourPolicy = bh;
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
    
    public void rename(String name) {
        this.name = name;
    }
    
    public void updateCategory(Categories categories) {
        this.categories = categories;
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
    
    public BusinessHourPolicy getBusinessHourPolicy() {
        return businessHourPolicy;
    }
    
    public String getIntroduce() {
        return introduce;
    }
    
    public Categories getCategories() {
        return this.categories;
    }
    
    public void addClosingDayPolicy(ClosingDayPolicy instance) {
        closingDayPolicies.addClosingDayPolicy(instance);
    }
    
    public ClosingDayPolicies getClosingDayPolicies() {
        return closingDayPolicies;
    }
    
    public boolean isClosingAt(LocalDate date) {
        return closingDayPolicies.isClosingAt(date);
    }
    
    public boolean isOpeningAt(LocalDateTime when) {
        return businessHourPolicy.isBusinessHour(when)
                && !suspension.isSuspended(when);
    }
    
    public boolean isExposed() {
        return exposed;
    }
    
    public void updateExposeStatus(Boolean expose) {
        this.exposed = expose;
    }
    
    public void suspend(Suspension suspension) {
        this.suspension = suspension;
    }
    
    public Suspension getSuspension() {
        return suspension;
    }
    
}
