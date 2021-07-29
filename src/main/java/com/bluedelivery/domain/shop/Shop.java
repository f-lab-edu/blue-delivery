package com.bluedelivery.domain.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.bluedelivery.domain.businesshour.BusinessHours;
import com.bluedelivery.domain.category.Categories;
import com.bluedelivery.domain.category.Category;
import com.bluedelivery.domain.closingday.ClosingDayPolicies;
import com.bluedelivery.domain.closingday.ClosingDayPolicy;
import com.bluedelivery.domain.closingday.Suspension;

@Entity
public class Shop {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BusinessHours businessHours;
    private String introduce;
    private String phone;
    private String deliveryAreaGuide;
    @Transient
    private Categories categories = new Categories();
    @Transient
    private ClosingDayPolicies closingDayPolicies = new ClosingDayPolicies();
    private boolean exposed;
    @Transient
    private Suspension suspension = new Suspension();
    
    public Shop() {
    }
    
    public Long getId() {
        return id;
    }
    
    public Categories getCategories() {
        return this.categories;
    }
    
    public boolean updateCategory(List<Category> categories) {
        return this.categories.updateAll(categories);
    }
    
    public void updateBusinessHour(BusinessHours bh) {
        businessHours = bh;
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
    
    public String getPhone() {
        return phone;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDeliveryAreaGuide() {
        return deliveryAreaGuide;
    }
    
    public BusinessHours getBusinessHours() {
        return businessHours;
    }
    
    public String getIntroduce() {
        return introduce;
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
        return businessHours.isBusinessHour(when)
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
