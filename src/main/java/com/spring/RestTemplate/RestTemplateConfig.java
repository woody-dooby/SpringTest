package com.spring.RestTemplate;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setBufferRequestBody(true);
        factory.setConnectTimeout(2000);
        factory.setReadTimeout(3000);

        //매우 재밌는 점 : connection pool 이라 함은 연결을 유지한체 connection pool 에 저장하게 된다.
        //그렇지만 , HTTP 프로토콜 특성상 Stateless 한 특징을 가져서 session, cookie 나 keep-alive 를 이용하여 StateFull 스럽게 사용하는 거 같다.
        //session 이나 cookie 는 제쳐 두고 k eep-alive 를 지원하지 않는 서버의 경우 아래와 같이 connection 을 200개를 만들어도 소용없이 지속적으로 맺고 끊으며 새롭게 connection 을 진행한다.
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(200) //connection 개수
                .setMaxConnPerRoute(20) // ip,port 하나당 연결 제한 개수.
                .build();

        factory.setHttpClient(httpClient);
        return new RestTemplate(factory);
    }
//      방식의 차이가 있을뿐 같다고 느낀다.
//        return  builder
//               .requestFactory(()-> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
//               .setConnectTimeout(Duration.ofMillis(1000))
//               .setReadTimeout(Duration.ofMillis(1000))
//               .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
//               .build();
}
