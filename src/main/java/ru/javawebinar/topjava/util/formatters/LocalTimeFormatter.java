package ru.javawebinar.topjava.util.formatters;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalTimeFormatter implements Formatter<LocalTime> {

    private String pattern;

    public LocalTimeFormatter(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public LocalTime parse(String localTime, Locale locale) throws ParseException {
        return (StringUtils.isEmpty(localTime)) ? null : LocalTime.parse(localTime);
    }

    @Override
    public String print(LocalTime localTime, Locale locale) {
        return (localTime == null) ? "" : localTime.format(DateTimeFormatter.ofPattern(pattern).withLocale(locale));
    }
}
