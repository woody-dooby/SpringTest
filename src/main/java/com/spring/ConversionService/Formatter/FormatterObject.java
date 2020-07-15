package com.spring.ConversionService.Formatter;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class FormatterObject {
    private String type;
    private String name;
    private List<String> list;
    private Map<String,Object> map;
}
