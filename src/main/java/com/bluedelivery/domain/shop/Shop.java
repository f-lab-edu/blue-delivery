package com.bluedelivery.domain.shop;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import com.bluedelivery.domain.category.Category;
import com.bluedelivery.domain.closingday.ClosingDayPolicies;
import com.bluedelivery.domain.closingday.ClosingDayPolicy;
import com.bluedelivery.domain.closingday.Suspension;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SHOP_ID")
    private Long id;
    private String name;
    private String introduce;
    private String phone;
    private String deliveryAreaGuide;
    
    @ElementCollection
    @CollectionTable(name = "BUSINESS_HOUR", joinColumns = @JoinColumn(name = "SHOP_ID"))
    private List<BusinessHour> businessHours = new ArrayList<>();
    
    @ElementCollection
    @CollectionTable(name = "SHOP_CATEGORY", joinColumns = @JoinColumn(name = "SHOP_ID"))
    private List<Long> categoryIds = new ArrayList<>();
    
    @Transient
    private ClosingDayPolicies closingDayPolicies = new ClosingDayPolicies();
    private boolean exposed;
    @Transient
    private Suspension suspension = new Suspension();
    
    public Shop() {
    }
    
    public void updateBusinessHours(List<BusinessHour> input) {
        this.businessHours.clear();
        this.businessHours.addAll(input);
    }
    
    public void updateCategoryIds(List<Category> categories) {
        List<Long> ids = categories.stream().map(each -> each.getId()).collect(Collectors.toList());
        this.categoryIds.clear();
        this.categoryIds.addAll(ids);
    }
    
    public List<BusinessHour> getBusinessHours() {
        return this.businessHours;
    }
    
    public List<Long> getCategoryIds() {
        return this.categoryIds;
    }
    
    public Long getId() {
        return id;
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
        return !suspension.isSuspended(when);
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
