package com.delivery.shop.category;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SearchShopByCategoryRequest {
    private Long id;
    private LocalDateTime now;
    private DayOfWeek dayOfWeek;
    
    public SearchShopByCategoryRequest(Long id, LocalDateTime now) {
        this.id = id;
        this.now = now;
        this.dayOfWeek = now.getDayOfWeek();
    }
    
    public LocalDateTime getNow() {
        return now;
    }
    
    @Override
    public String toString() {
        return "SearchShopByCategoryParam{" + "id=" + id + ", now=" + now + ", dayOfWeek=" + dayOfWeek + '}';
    }
}
