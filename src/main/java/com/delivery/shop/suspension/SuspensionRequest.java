package com.delivery.shop.suspension;

import java.time.LocalDateTime;

public class SuspensionRequest {
    private enum Period {
        NONE(0, 0),
        HALF_HOUR(0, 30),
        ONE_HOUR(1, 0),
        ONE_HOUR_AND_HALF(1, 30),
        TWO_HOURS(2, 0),
        THREE_HOURS(3, 0),
        FOUR_HOURS(4, 0);
    
        private int hours;
        private int minutes;
    
        Period(int hours, int minutes) {
            this.hours = hours;
            this.minutes = minutes;
        }
    }
    
    private LocalDateTime now;
    private Period period;
    
    public SuspensionRequest(LocalDateTime now, Period period) {
        this.now = now;
        this.period = period;
    }
    
    public Suspension toEntity() {
        return new Suspension(
                this.now,
                this.toUntil()
        );
    }
    
    private LocalDateTime toUntil() {
        if (period == Period.NONE) {
            return now;
        }
        return now.plusHours(period.hours).plusMinutes(period.minutes);
    }
}
