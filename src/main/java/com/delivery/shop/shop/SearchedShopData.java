package com.delivery.shop.shop;

import java.time.LocalDateTime;

import com.delivery.shop.businesshour.BusinessHour;
import com.delivery.shop.closingday.ClosingDayPolicies;
import com.delivery.shop.suspension.Suspension;
import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO 나중에 대표메뉴 조회결과, 평점, 배달소요시간, 최소주문, 배달팁 정보 등등 추가되어야 함
public class SearchedShopData {
    private Long id;
    private String name;
    private BusinessHour todayHours;
    @JsonIgnore
    private ClosingDayPolicies closingDayPolicies;
    private LocalDateTime now;
    private Suspension suspension;
    
    
    public SearchedShopData() {
    }
    
    public SearchedShopData(Long id, String name, BusinessHour todayHours, ClosingDayPolicies cd) {
        this.id = id;
        this.name = name;
        this.todayHours = todayHours;
        this.closingDayPolicies = cd;
        this.suspension = new Suspension();
    }
    
    public SearchedShopData(Long id, String name, BusinessHour todayHours, ClosingDayPolicies closingDayPolicies,
                            Suspension suspension) {
        this.id = id;
        this.name = name;
        this.todayHours = todayHours;
        this.closingDayPolicies = closingDayPolicies;
        this.suspension = suspension;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTodayHours(BusinessHour todayHours) {
        this.todayHours = todayHours;
    }
    
    public void setClosingDayPolicies(ClosingDayPolicies closingDayPolicies) {
        this.closingDayPolicies = closingDayPolicies;
    }
    
    public void setSuspension(Suspension suspension) {
        this.suspension = suspension;
    }
    
    public boolean isClosingDay() {
        return closingDayPolicies.isClosingAt(this.now.toLocalDate());
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public BusinessHour getTodayHours() {
        return todayHours;
    }
    
    public boolean isOpening() {
        return todayHours.isOpening(this.now.toLocalTime())
                && !suspension.isSuspended(now);
    }
    
    public SearchedShopData setNow(LocalDateTime now) {
        this.now = now;
        return this;
    }
    
    public Suspension getSuspension() {
        return suspension;
    }
}
