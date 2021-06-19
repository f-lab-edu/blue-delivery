package com.delivery.shop.businesshour;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class BusinessHour {
    
    private Long shopId;
    private LocalTime open;
    private LocalTime close;
    private DayOfWeek dayOfWeek;
    
    public BusinessHour() {
    }
    
    public BusinessHour(DayOfWeek dayOfWeek, LocalTime open, LocalTime close) {
        this.dayOfWeek = dayOfWeek;
        this.open = open;
        this.close = close;
    }
    
    public BusinessHour(Long shopId, LocalTime open, LocalTime close, DayOfWeek dayOfWeek) {
        this.shopId = shopId;
        this.open = open;
        this.close = close;
        this.dayOfWeek = dayOfWeek;
    }
    
    public Long getShopId() {
        return shopId;
    }
    
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
    
    public LocalTime getOpen() {
        return open;
    }
    
    public LocalTime getClose() {
        return close;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BusinessHour that = (BusinessHour) obj;
        return Objects.equals(open, that.open) && Objects.equals(close, that.close);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(open, close);
    }
    
    /**
     * #isOpening()
     * 영업종료시간이 익일로 넘어가는 경우 현재시간은 23시인데 close가 새벽 3시라 영업시간이 아니게 되는 문제가 발생할 수 있다.
     * 그래서 이 경우 다음 두 케이스를 비교한다.
     * - 1. open ~ 자정 직전
     * - 2. 자정 ~ close
     * midnight.minusNanos(1L) : 자정을 24시 00분으로 표현할 수가 없어서 1 nano 를 깎아 23시 59분 59초 ... 로 최대치를 만들기 위함
     *
     * @param now 영업 시간에 포함되는지 확인할 시간
     * @return 영업중이 맞다면 true
     */
    public boolean isOpening(LocalTime now) {
        LocalTime midnight = LocalTime.of(00, 00);
        if (close.isBefore(open)) { // 영업종료시간이 익일인 경우
            if ((open.isBefore(now) && now.isBefore(midnight.minusNanos(1L)))
                    || (midnight.isBefore(now) && now.isBefore(close))) {
                return true;
            }
            return false;
        }
        return open.isBefore(now) && now.isBefore(close);
        
    }
    
    @Override
    public String toString() {
        return "BusinessHour{"
                + "shopId=" + shopId + ", open=" + open + ", close=" + close
                + ", dayOfWeek=" + dayOfWeek + '}';
    }
}
