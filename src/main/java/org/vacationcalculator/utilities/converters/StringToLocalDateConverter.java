package org.vacationcalculator.utilities.converters;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDate>{
    @Override
    public LocalDate convert(String from, Class<LocalDate> to) {
        return LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
