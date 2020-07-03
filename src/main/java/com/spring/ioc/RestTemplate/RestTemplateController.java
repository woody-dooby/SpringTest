package com.spring.ioc.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class RestTemplateController {


    @RequestMapping(value = "/restTest",method = RequestMethod.GET, produces = "application/xml")
    public XmlVo testXmlData(){
        return new XmlVo();
    }
    //json return
    @RequestMapping(value = "/json",method = RequestMethod.GET)
    public JsonVo testJsonData() {
        return new JsonVo();
    }
    //check header
    @RequestMapping(value = "/entity",method = RequestMethod.GET)
    public ResponseEntity<String> checkHeader(String name, HttpServletRequest httpServletRequest) {
        log.info("Hello!!!!!!!! {}", name);
        if(!httpServletRequest.getHeader("Authentication").equals("LEMON")) {
            return new ResponseEntity<>("permission denied", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("welcome!", HttpStatus.OK);
    }

    //post
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public ResponseEntity<String> postForEntity(String contents){
        log.info("requestbody: {}", contents);
        return new ResponseEntity<>("Success Response", HttpStatus.OK);
    }
}
