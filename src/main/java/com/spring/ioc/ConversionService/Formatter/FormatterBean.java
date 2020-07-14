package com.spring.ioc.ConversionService.Formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class FormatterBean implements Formatter<FormatterObject> {
    @Override
    public FormatterObject parse(String str, Locale locale) throws ParseException {
        FormatterObject obj = new FormatterObject();
        obj.setName(str.split("\\|")[0]);
        obj.setType(str.split("\\|")[1]);
        return obj;
    }

    @Override
    public String print(FormatterObject obj, Locale locale) {
        return null;
    }
}
