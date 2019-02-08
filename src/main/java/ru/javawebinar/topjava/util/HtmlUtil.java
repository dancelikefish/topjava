package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HtmlUtil {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
    public static String formatDate(LocalDateTime dateTime) {
        return dateTime.format(dateTimeFormatter);
    }
}
