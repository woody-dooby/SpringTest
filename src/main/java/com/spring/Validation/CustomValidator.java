package com.spring.Validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CustomValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ValidatorObject.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        //support 를 통과하게 되어서 바로 캐스팅 해도 문제가 없음
        ValidatorObject object = (ValidatorObject)target;
        //error code
        /*
        Spring messageCodeResolver 르 거쳐서 에러코드를 확장하는 작업을 거친다. (등록된 에러코드 `name.required`는 아래와 같은 4가지 에러코드로 확장된다.)

        에러코드.모델이름.필드이름 : name.required.user.name
        에러코드.필드이름 : name.required.name
        에러코드.타입이름 : name.required.User
        에러코드 : name.required

        위에서부터 우선순위를 가진다.
        e.g)
        `messages_ko.properties` 파일
        ~ name.required.user.name=무언가 잘못된 메세지
        ~ name.required=이름은 필수로 입력하셔야 합니다.
        등록되어 있으면  `name.required`의 답변이 아닌 `name.required.user.name`를 가지게 된다.
        */
        if(!object.getType().equals("TEST") && object.getName() != null) {
            //요렇게도 가능하다.
            errors.reject("error");
            return;
        }
        if(object.getName() != null){
            errors.rejectValue("name","name.required");
            return;
        }
        // null 체크 & 길이 체크가 귀찮으면 아래와 같이 사용 가능
        // ValidationUtils 을 잘 활용하면 좋다.
        ValidationUtils.rejectIfEmpty(errors, "name", "name.required");
        if(object.getType().equals("TEST")){
            //argument 와 default message 도 가능하다.
            errors.rejectValue("type","type.required",new Object[]{0},null);
        }
    }
}
