package ru.javawebinar.topjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static LocalDate toLocalDateOrMax(String localDate) {
        return localDate == null || localDate.equals("") ? LocalDate.MAX : LocalDate.parse(localDate, DATE_FORMATTER);
    }

    public static LocalDate toLocalDateOrMin(String localDate) {
        return localDate == null || localDate.equals("") ? LocalDate.MIN : LocalDate.parse(localDate, DATE_FORMATTER);
    }


    public static LocalTime toLocalTimeOrMax(String localTime) {
        return localTime == null || localTime.equals("") ? LocalTime.MAX : LocalTime.parse(localTime, TIME_FORMATTER);
    }

    public static LocalTime toLocalTimeOrMin(String localTime) {
        return localTime == null || localTime.equals("") ? LocalTime.MIN : LocalTime.parse(localTime, TIME_FORMATTER);
    }

    public static <T> boolean isBetweenDateOrTime(T actualTime, T startTime, T endTime) {
        return ((Comparable) actualTime).compareTo(startTime) >= 0 && ((Comparable) actualTime).compareTo(endTime) <= 0;
    }
}
