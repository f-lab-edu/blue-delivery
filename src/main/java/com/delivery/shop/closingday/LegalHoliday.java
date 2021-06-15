package com.delivery.shop.closingday;

import static com.delivery.shop.closingday.CalendarType.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

import net.time4j.PlainDate;
import net.time4j.calendar.ChineseCalendar;

public enum LegalHoliday {
    // LUNAR_NEW_YEAR_DAY_EVE의 경우만 '전년도' 말일로 년도가 다름. 그래서 설날-1일인 day=0로 설정.
    LUNAR_NEW_YEARS_DAY_EVE(LUNAR, Month.JANUARY, 0),
    LUNAR_NEW_YEARS_DAY(LUNAR, Month.JANUARY, 1),
    LUNAR_NEW_YEARS_DAY_AFTER(LUNAR, Month.JANUARY, 2),
    BUDDHA_S_BIRTHDAY(LUNAR, Month.APRIL, 8),
    CHUSEOK_EVE(LUNAR, Month.AUGUST, 14),
    CHUSEOK(LUNAR, Month.AUGUST, 15),
    CHUSEOK_AFTER(LUNAR, Month.AUGUST, 16),
    
    SOLAR_NEW_YEARS_DAY(SOLAR, Month.JANUARY, 1),
    INDEPENDENCE_MOVEMENT_DAY(SOLAR, Month.MARCH, 1),
    CHILDREN_S_DAY(SOLAR, Month.MAY, 5),
    MEMORIAL_DAY(SOLAR, Month.JUNE, 6),
    LIBERATION_DAY(SOLAR, Month.AUGUST, 15),
    NATION_FOUNDATION_DAY_OF_KOREA(SOLAR, Month.OCTOBER, 3),
    HANGUL_PROCLAMATION_DAY(SOLAR, Month.OCTOBER, 9),
    CHRISTMAS(SOLAR, Month.DECEMBER, 25);
    
    private CalendarType calendarType;
    private Month month;
    private int day;
    
    LegalHoliday(CalendarType calendarType, Month month, int day) {
        this.calendarType = calendarType;
        this.month = month;
        this.day = day;
    }
    
    public static LocalDate transformToSolar(Year year, LegalHoliday holiday) {
        if (holiday.calendarType == LUNAR) {
            ChineseCalendar chineseCalendar = ChineseCalendar.ofNewYear(year.getValue());
            chineseCalendar = chineseCalendar
                    .plus(holiday.month.getValue() - 1, ChineseCalendar.Unit.MONTHS)
                    .plus(holiday.day - 1, ChineseCalendar.Unit.DAYS);
            PlainDate lunar = chineseCalendar.transform(PlainDate.axis());
            System.out.println("lunar = " + lunar);
            return LocalDate.of(lunar.getYear(),
                    lunar.getMonth(),
                    lunar.getDayOfMonth());
        }
        return LocalDate.of(year.getValue(), holiday.month, holiday.day);
    }
}
