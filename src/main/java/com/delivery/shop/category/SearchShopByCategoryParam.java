package com.delivery.shop.category;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SearchShopByCategoryParam {
    private Long id;
    private LocalDateTime now;
    private DayOfWeek dayOfWeek;
    
    public SearchShopByCategoryParam(Long id, LocalDateTime now) {
        this.id = id;
        this.now = now;
        this.dayOfWeek = now.getDayOfWeek();
    }
}
