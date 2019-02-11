package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public static String formatDate(LocalDateTime dateTime) {
        if (dateTime != null) {
            return dateTime.format(DATE_TIME_FORMATTER);
        }
        return null;
    }
}
