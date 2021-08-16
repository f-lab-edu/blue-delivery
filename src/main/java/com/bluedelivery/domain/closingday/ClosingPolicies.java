package com.bluedelivery.domain.closingday;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Embeddable
public class ClosingPolicies {
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SHOP_ID")
    private List<ClosingPolicy> closingDays = new ArrayList<>();
    
    public ClosingPolicies() {
    
    }
    
    public void add(ClosingPolicy instance) {
        closingDays.add(instance);
    }
    
    public boolean isClosed(LocalDateTime datetime) {
        return closingDays.stream().anyMatch(x -> x.isClosed(datetime));
    }
    
}
