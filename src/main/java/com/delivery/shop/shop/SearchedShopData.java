package com.delivery.shop.shop;

import java.time.LocalDateTime;

import com.delivery.shop.businesshour.BusinessHourPolicy;
import com.delivery.shop.closingday.ClosingDayPolicies;

// TODO 나중에 대표메뉴 조회결과, 평점, 배달소요시간, 최소주문, 배달팁 정보 등등 추가되어야 함
public class SearchedShopData {
    private Long id;
    private String name;
    private BusinessHourPolicy businessHourPolicy;
    private ClosingDayPolicies closingDayPolicies;
    
    public SearchedShopData() {
    }
    
    public SearchedShopData(Long id, String name, BusinessHourPolicy bh, ClosingDayPolicies cd) {
        this.id = id;
        this.name = name;
        this.businessHourPolicy = bh;
        this.closingDayPolicies = cd;
    }
    
    public boolean isOpening(LocalDateTime datetime) {
        return !closingDayPolicies.isClosingAt(datetime.toLocalDate())
                && businessHourPolicy.isBusinessHour(datetime);
    }
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public BusinessHourPolicy getBusinessHourPolicy() {
        return businessHourPolicy;
    }
    
    public ClosingDayPolicies getClosingDayPolicies() {
        return closingDayPolicies;
    }
}
