package com.bluedelivery.shop.search;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SearchShopByCategoryParam {
    private Long id;
    private LocalDateTime now;
    
    public SearchShopByCategoryParam(Long id, LocalDateTime now) {
        this.id = id;
        this.now = now;
    }
    
    public Long getId() {
        return id;
    }
    
    public LocalDateTime getNow() {
        return now;
    }
    
    public DayOfWeek getDayOfWeek() {
        return now.getDayOfWeek();
    }
    
}
