package com.delivery.shop.closingday;

import static com.delivery.shop.closingday.CalendarType.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    
    /**
     * 주어진 해의 모든 공휴일과 대체공휴일을 양력으로 변환한다.
     *
     * @param year 연도
     * @return 양력으로 계산된 LocalDate 타입의 Set
     */
    // TODO 현재 법정공휴일에서 일요일은 제외한 상태인데, 일요일은 영업하고 대체공휴일인 월요일에 휴무인 상황이 생김. 이대로 상관없을지 고민
    public static Set<LocalDate> getSolarDays(Year year) {
        // 음력->양력 변환시 기존 양력 공휴일과 겹치는 날이 있을 수 있으므로 계산을 위해 따로 리스트로 받아놓는다.
        List<LocalDate> basedOnLunarList = new ArrayList<>();
        List<LocalDate> holidays = new ArrayList<>();
        
        for (LegalHoliday holiday : LegalHoliday.values()) {
            LocalDate solar = LegalHoliday.transformToSolar(year, holiday);
            if (holiday.calendarType == LUNAR) {
                basedOnLunarList.add(solar);
                continue;
            }
            holidays.add(solar);
        }
        return addSubstituteHoliday(holidays, basedOnLunarList);
    }
    
    /**
     * 주어진 날짜를 양력으로 변환한다.
     *
     * @param year    연도
     * @param holiday [음력,양력] 타입과 월/일 정보
     * @return 양력으로 계산된 LocalDate 타입
     */
    private static LocalDate transformToSolar(Year year, LegalHoliday holiday) {
        if (holiday.calendarType == LUNAR) {
            ChineseCalendar chineseCalendar = ChineseCalendar.ofNewYear(year.getValue());
            chineseCalendar = chineseCalendar
                    .plus(holiday.month.getValue() - 1, ChineseCalendar.Unit.MONTHS)
                    .plus(holiday.day - 1, ChineseCalendar.Unit.DAYS);
            PlainDate lunar = chineseCalendar.transform(PlainDate.axis());
            return LocalDate.of(lunar.getYear(),
                    lunar.getMonth(),
                    lunar.getDayOfMonth());
        }
        return LocalDate.of(year.getValue(), holiday.month, holiday.day);
    }
    
    /**
     * 대체공휴일
     * - 음력 공휴일이 양력으로 변환시 양력 공휴일과 겹치는지 확인 후 대체공휴일 생성
     * - 전체 공휴일에 대해 주말인지 확인 후 대체공휴일 생성
     * - 대체공휴일 날짜는 연휴가 끝나는 시점.
     * - ex) 일요일 설날, 연휴가 토,월 -> 화요일을 대체공휴일로 지정.
     **/
    private static Set<LocalDate> addSubstituteHoliday(List<LocalDate> holidays,
                                                       List<LocalDate> basedOnLunarList) {
        lunarDuplicateCheck(holidays, basedOnLunarList);
        weekendCheck(holidays);
        return new HashSet<>(holidays);
    }
    
    private static void weekendCheck(List<LocalDate> holidays) {
        List<LocalDate> temp = new ArrayList<>();
        for (LocalDate holiday : holidays) {
            LocalDate sub = holiday.plusDays(0);
            while (sub.getDayOfWeek() == DayOfWeek.SATURDAY
                    || sub.getDayOfWeek() == DayOfWeek.SUNDAY) {
                sub = sub.plusDays(1);
            }
            if (holiday.equals(sub)) {
                continue;
            }
            temp.add(sub);
        }
        holidays.addAll(temp);
    }
    
    private static void lunarDuplicateCheck(List<LocalDate> holidays,
                                            List<LocalDate> basedOnLunarList) {
        for (LocalDate lunar : basedOnLunarList) {
            while (holidays.contains(lunar)) {
                lunar = lunar.plusDays(1);
            }
            holidays.add(lunar);
        }
    }
}
