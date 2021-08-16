package com.bluedelivery.domain.closingday;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateTimeConverter {
    public static LocalDateTime toLocalDateTime(LocalDate lastMondayOnJune) {
        return LocalDateTime.of(lastMondayOnJune, LocalTime.MIN);
    }
    
    public static LocalDateTime toLocalDateTime(LocalTime lastMondayOnJune) {
        return LocalDateTime.of(LocalDate.MIN, lastMondayOnJune);
    }
}

