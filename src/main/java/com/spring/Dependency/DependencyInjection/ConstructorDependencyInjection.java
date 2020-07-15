package com.spring.Dependency.DependencyInjection;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Dependency Injection : 의존성을 주입시 Spring IoC가 자동으로 의존성을 주입하여 방식..
 *  - 그 중 생성자 주입 방식이다.
 *  - 모든 의존성 주입 시
 */
@Component
@Slf4j
public class ConstructorDependencyInjection {
    //@Autowired -> Spring version up 으로 굳이 필요없음
    public DIBean bean;

    //Constructor
    ConstructorDependencyInjection(DIBean bean){
        //! 의문점.
        // default scope 가 singleton 인데 생성자가 2번 호출?
        // 실행보면 hashcode 가 같다.
        log.info(ConstructorDependencyInjection.class.getSimpleName() + " TEST");
        this.bean = bean;
        bean.hello();

    }

}
