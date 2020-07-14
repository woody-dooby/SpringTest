package com.spring.ioc.ConversionService.Convert;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ConverterObject {
    private String type;
    private String name;
    private List<String> list;
    private Map<String,Object> map;
}
