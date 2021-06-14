package com.delivery.shop.closingday;

import java.time.LocalDate;

public interface ClosingDay {
    boolean isClosedAt(LocalDate localDate);
}
