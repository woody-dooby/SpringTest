package com.spring.ioc.MessageSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
public class MessageSourceConfig implements WebMvcConfigurer {

    //LocalResolver를 설정하기위하여 LocaleContextResolver(interface)를 상속받은 구현체를 생성하는 것이 좋다.
    @Bean
    @ConditionalOnMissingBean
    public LocaleResolver localeResolver() {
        // 세션에 지역설정. -> 해당 설정에 따라 messageSource 가 달라짐.
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.KOREAN);
        return slr;
    }
    //부가적인 기능.. WebMvcConfigurer 로 config 설정 변경시 사용
    @Bean // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override // 인터셉터를 시스템 레지스트리에 등록
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    @Bean // yml 파일을 참조하는 MessageSource 선언
    public MessageSource messageSource(@Value("${spring.messages.basename}") String basename) {
        ResourceBundleMessageSource ms = new ResourceBundleMessageSource(){
//            @Override
//            protected ResourceBundle doGetBundle(String basename, Locale locale){
//                //흠 여기를 고치면 .properties 말고도 .yml 같은 파일을 읽을 수 있게찌..
//                return null;
//            }
        };
        // 파일명_언어코드_국가코드.properties
        ms.setBasename(basename); //"classpath:/message_resource/message"
        // 파일인코딩.
        ms.setDefaultEncoding(StandardCharsets.UTF_8.name());
        // 포멧을 전체 메시지에 적용할 것인지.
        ms.setAlwaysUseMessageFormat(true);
        // 메시지를 찾지 못했을때 예외 처리 대신 메시지 코드를 반환
        ms.setUseCodeAsDefaultMessage(false);
        // 감지된 local에 대한 파일이 없는 경우 system local 사용할 것인지(true), messages.properties 를 사용할 것인지(false)
        ms.setFallbackToSystemLocale(true);
        return ms;
    }
}
