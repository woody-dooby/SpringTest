package com.spring.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/*
 * Spring에서 Default로 사용하는 (De)Serializer인 Jackson이 알 수 없는 타입은 LinkedHashMap으로 강제 전환해버리기 때문이다
 * 출처: https://preamtree.tistory.com/167 [Preamtree의 행복로그]
*/
@Service
public class RestTemplateAPIService<T> {

    private RestTemplate restTemplate;

    @Autowired
    public RestTemplateAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders) {
        return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, (Class<T>)Object.class);
    }

    public ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
        return callApiEndpoint(url, HttpMethod.GET, httpHeaders, null, clazz);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body) {
        return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body,(Class<T>)Object.class);
    }

    public ResponseEntity<T> post(String url, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return callApiEndpoint(url, HttpMethod.POST, httpHeaders, body, clazz);
    }

    private ResponseEntity<T> callApiEndpoint(String url, HttpMethod httpMethod, HttpHeaders httpHeaders, Object body, Class<T> clazz) {
        return restTemplate.exchange(url, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
    }

    //GET
    public XmlVo getXmlResponse(String url){
        return restTemplate.getForObject(url, XmlVo.class);
    }
    //GET
    public JsonVo getJsonRsponse(String url) {
        return restTemplate.getForObject(url, JsonVo.class);
    }
}
