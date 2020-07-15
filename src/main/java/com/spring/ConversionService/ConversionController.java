package com.spring.ConversionService;

import com.spring.ConversionService.Convert.ConverterObject;
import com.spring.ConversionService.Formatter.FormatterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConversionController {

    //기본적으로 ApplicationConversionService 에서 등록된 기본 conversionService converter 들이 있다.
    //아래 WebMVC 에서 사용되는 conversionService와 비교해 보자
    @Autowired
    ConfigurableEnvironment environment;

    @Autowired
    ConversionService conversionService;

    @GetMapping("/convert/{object}")
    public String getConverterToString(@PathVariable("object") ConverterObject object){
        return conversionService.convert(object,String.class);
    }

    @GetMapping("/format/{object}")
    public String getFormatterToString(@PathVariable("object") FormatterObject object){
        return conversionService.convert(object,String.class);
    }
}
