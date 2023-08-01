package com.idstar.trainingkaryawan.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class StringToDateConverter implements Converter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date convert(@Nullable String source) {
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            log.warn("Error convert date from string {}", source, e);
            return null;
        }
    }
}
