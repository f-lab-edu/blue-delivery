package com.delivery.shop.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.category.Category;
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
    private List<Category> categories;
    private ClosingDayPolicies closingDayPolicies;
    private boolean exposed;
    private Suspension suspension;
    
    public Shop() {
        closingDayPolicies = new ClosingDayPolicies();
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
    
    public void updateCategory(List<Category> categories) {
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
    
    public List<Category> getCategories() {
        return categories;
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
