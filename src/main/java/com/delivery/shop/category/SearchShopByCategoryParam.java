package com.delivery.shop.category;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class SearchShopByCategoryParam {
    private Long id;
    private Integer offset;
    private LocalDateTime now;
    private DayOfWeek dayOfWeek;
    
    public SearchShopByCategoryParam(Long id, Integer offset, LocalDateTime now) {
        this.id = id;
        this.offset = offset;
        this.now = now;
        this.dayOfWeek = now.getDayOfWeek();
    }
}
