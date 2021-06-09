package com.delivery.shop.shop;

import com.delivery.shop.businesshour.BusinessHourPolicy;

// TODO 나중에 대표메뉴 조회결과, 평점, 배달소요시간, 최소주문, 배달팁 정보 등등 추가되어야 함
public class SearchedShopData {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHourPolicy;
    
    public SearchedShopData() {
    }
    
    public SearchedShopData(Long id, String name, BusinessHourPolicy businessHourPolicy) {
        this.id = id;
        this.name = name;
        this.businessHourPolicy = businessHourPolicy;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public BusinessHourPolicy getBusinessHourPolicy() {
        return businessHourPolicy;
    }
    
    public void setBusinessHourPolicy(BusinessHourPolicy businessHourPolicy) {
        this.businessHourPolicy = businessHourPolicy;
    }
}
