package com.spring.ResourceLoader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@Slf4j
public class ResourceLoaderRunner implements ApplicationRunner {

    @Autowired
    private ResourceLoader loader;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Resource resource = loader.getResource("classpath:test.txt");
        // getFile 을 지양한다.!!
        // getFile 을 사용시에는 운영 배포시에 FileSystem 에서 읽을 수 있는 경로 이다.
        // jar 나 war 로 묶이게 된다면 리눅스 환경의 FileSystem 에서는 읽지 못한다. ( Jar, War 안에 존재하는 path에는 존재하겠지..)
        boolean bool = resource.getFile().isFile();
        // 그래서 해당 파일을 읽을 경우 바로 읽어서 처리하는 걸 추천한다.
        InputStream inputStream = resource.getInputStream();
        log.info("isFile ? "+bool);
        log.info("File size :" +inputStream.available());
    }
}
