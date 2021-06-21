package com.delivery.shop.suspension;

import java.time.LocalDateTime;

public class Suspension {
    private LocalDateTime from;
    private LocalDateTime to;
    
    public Suspension(LocalDateTime from, LocalDateTime to) {
        this.from = from;
        this.to = to;
    }
}
