package com.spring.ConversionService;//package com.spring.ioc.ConversionService;
//
//import com.spring.ioc.ConversionService.Convert.ConverterBean;
//import com.spring.ioc.ConversionService.Formatter.FormatterBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///* 해당 방식을 사용하는 것은 Converter 와 Formatter 를 따로 Bean 으로 등록하지 않는 기존 SpringFrameWork 의 방식이다.
//* Spring boot 에서는 WebConversionService 를 통해서 @Bean 으로 등록된 converter, formatter 를 자동으로 추가하여준다.*/
//@Configuration
//public class CoversionWebMvcConfigurerConfig implements WebMvcConfigurer {
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new FormatterBean());
//        registry.addConverter(new ConverterBean.ConverterToString());
//        registry.addConverter(new ConverterBean.StringToConverter());
//    }
//}
