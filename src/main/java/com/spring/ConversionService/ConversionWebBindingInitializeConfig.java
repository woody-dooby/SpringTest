package com.spring.ConversionService;//package com.spring.ioc.ConversionService;
//
//import com.spring.ioc.ConversionService.Convert.ConverterBean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.support.DefaultConversionService;
//import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
//
///**
// * 요청시에 적용되는 Binder 이다.
// */
//@Configuration(value = "conversionBC")
//public class ConversionWebBindingInitializeConfig extends ConfigurableWebBindingInitializer {
////        ConversionServiceFactoryBean 은 아래와 같은 방식으로 conversion Service 에 converter 들을 등록하여 사용한다.
////        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
////        ConversionServiceFactory.registerConverters(
////                Stream.of(new ConverterBean.ConverterToString(),new ConverterBean.StringToConverter()).collect(Collectors.toSet())
////                , new DefaultConversionService());
//    {
//        DefaultConversionService conversionService = new DefaultConversionService();
//        conversionService.addConverter(new ConverterBean.ConverterToString());
//        conversionService.addConverter(new ConverterBean.StringToConverter());
//        this.setConversionService(conversionService);
//    }
//
//}
