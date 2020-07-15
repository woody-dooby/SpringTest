package com.spring.ioc.RestTemplate;

import com.spring.RestTemplate.JsonVo;
import com.spring.RestTemplate.RestTemplateAPIService;
import com.spring.RestTemplate.XmlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collections;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class RestTemplateTester {

    @Autowired
    RestTemplateAPIService<String> service;

//    @Autowired
//    TestRestTemplate testTemplate; --> 요걸로 테스트 하는게 맞다. 다만, 현재는 만든 것에 대한 테스트를 하는 것이니 이해..

    private final String url = "http://127.0.0.1:8080/";

    @Test
    void xmlTest(){
        XmlVo vo = service.getXmlResponse(url + "xml");
        log.info(vo.getName());
    }
    @Test
    void jsonTest(){
        JsonVo vo = service.getJsonRsponse(url + "json/RestTemplateJSON");
        log.info(vo.getName());
    }
    @Test
    void headerTest(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authentication","LEMON");

        ResponseEntity<String> entity =service.get(url + "checkHeader", headers,String.class);

        Assertions.assertEquals(entity.getStatusCodeValue() ,200);
        Assertions.assertEquals(entity.getBody(),"welcome!");

        headers.set("Authentication","LEMON2");
        Assertions.assertThrows(HttpClientErrorException.class, ()-> service.get(url + "checkHeader", headers));
    }
    @Test
    void postTest(){
        ResponseEntity<String> entity = service.post(url+"post",null,Collections.singletonMap("contents","test"),String.class);
        Assertions.assertEquals(entity.getStatusCodeValue() ,200);
        Assertions.assertEquals(entity.getBody() ,"Success Response");
    }

}