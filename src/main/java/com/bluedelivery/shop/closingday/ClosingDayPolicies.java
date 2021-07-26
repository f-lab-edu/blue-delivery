package com.bluedelivery.shop.closingday;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClosingDayPolicies {
    private List<ClosingDayPolicy> closingDays;
    
    public ClosingDayPolicies() {
        this.closingDays = new ArrayList<>();
    }
    
    public void addClosingDayPolicy(ClosingDayPolicy instance) {
        if (closingDays == null) {
            closingDays = new ArrayList<>();
        }
        closingDays.add(instance);
    }
    
    public List<ClosingDayPolicy> getClosingDays() {
        return Collections.unmodifiableList(closingDays);
    }
    
    public boolean isClosingAt(LocalDate date) {
        return closingDays.stream().anyMatch(x -> x.isClosedAt(date));
    }
    
}
