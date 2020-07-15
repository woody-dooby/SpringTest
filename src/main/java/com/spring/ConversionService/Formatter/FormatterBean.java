package com.spring.ConversionService.Formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class FormatterBean implements Formatter<FormatterObject> {
    @Override
    public FormatterObject parse(String str, Locale locale) throws ParseException {
        FormatterObject obj = new FormatterObject();
        obj.setType(str.split("\\|")[0]);
        obj.setName(str.split("\\|")[1]);
        return obj;
    }

    @Override
    public String print(FormatterObject obj, Locale locale) {
        return Stream.of(obj.getClass().getDeclaredFields()).map(field->{
            field.setAccessible(Boolean.TRUE);
            try {
                Object object = field.get(obj);
                if(object instanceof String){
                    return (CharSequence)object;
                }
                return null;
            } catch (IllegalAccessException e) {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.joining("|"));
    }
}
