package com.spring.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomPropertyRunner implements ApplicationRunner {

    final Environment environment;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("CustomPropertyApplicationEnvironmentPreparedEvents TEST START" );
        log.info("test case :  {}",environment.getProperty("test.case"));
        log.info("test name :  {}",environment.getProperty("test.name"));
        log.info("CustomPropertyApplicationEnvironmentPreparedEvents TEST END");

        log.info("CustomPropertyEnvironmentPostProcessor TEST START" );
        log.info("post case :  {}",environment.getProperty("post.case"));
        log.info("post name :  {}",environment.getProperty("post.name"));
        log.info("CustomPropertyEnvironmentPostProcessor TEST END");
    }
}
