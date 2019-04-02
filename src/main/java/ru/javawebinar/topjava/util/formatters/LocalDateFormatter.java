package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateFormatter implements Formatter<LocalDate> {

    private String pattern;

    public LocalDateFormatter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalDate parse(String text, Locale locale) throws ParseException {
        return (StringUtils.isEmpty(text)) ? null : LocalDate.parse(text);
    }

    @Override
    public String print(LocalDate localDate, Locale locale) {
        return (localDate == null) ? "" : localDate.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale));
    }
}
