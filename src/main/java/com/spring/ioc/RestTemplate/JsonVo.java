package com.spring.ioc.RestTemplate;

import lombok.Data;

@Data
//@JsonDeserialize(using = JsonCustomDeserializer.class)
//@JsonIgnoreProperties(ignoreUnknown = true)     //이 클래스에 정의되지 않은 필드가 있을 경우에 무시하겠다는 뜻이다.
public class JsonVo {
    private String name;

    //jackson 을 이용한 json 이용으로
    JsonVo(){}
    JsonVo(String name){
        this.name = name;
    }

}
