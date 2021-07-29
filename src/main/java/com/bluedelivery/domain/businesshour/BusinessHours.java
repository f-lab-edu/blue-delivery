package com.bluedelivery.domain.businesshour;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.bluedelivery.domain.shop.Shop;

@Embeddable
public class BusinessHours {
    @OneToMany(mappedBy = "shop", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    protected Set<BusinessHour> businessHours;
    
    protected BusinessHours() {
    }
    
    public BusinessHours(Collection<BusinessHour> hours) {
        businessHours = new LinkedHashSet<>(hours);
    }
    
    public BusinessHour getBusinessHourOf(DayOfWeek day) {
        return businessHours.stream()
                .filter(x -> x.getDayOfWeek() == day)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("dayOfWeek does not exist for this shop"));
    }
    
    public Set<BusinessHour> getBusinessHours() {
        return Collections.unmodifiableSet(businessHours);
    }
    
    public boolean isBusinessHour(LocalDateTime date) {
        return getBusinessHourOf(date.getDayOfWeek())
                .isOpening(date.toLocalTime());
    }
    
    public void setShop(Shop shop) {
        businessHours.forEach( x-> x.setShop(shop));
        shop.updateBusinessHour(this);
    }
}
