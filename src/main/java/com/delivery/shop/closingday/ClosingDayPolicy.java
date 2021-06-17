package com.delivery.shop.closingday;

import java.time.LocalDate;

public interface ClosingDayPolicy {
    /**
     * 주어진 날짜가 지정한 휴무일과 일치하는지 확인한다.
     * 상속받은 클래스는 휴무일 타입 이름을 나타내는 private static인 CLOSING_TYPE를 가지고 있다.
     * @param date 확인하고 싶은 날짜
     * @return 휴무일에 해당한다면 true
     */
    boolean isClosedAt(LocalDate date);
}
