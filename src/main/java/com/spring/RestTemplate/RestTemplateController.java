package com.spring.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Slf4j
public class RestTemplateController {

    //xml return
    @RequestMapping(value = "/xml",method = RequestMethod.GET, produces = "application/xml")
    public XmlVo testXmlData(){
        return new XmlVo();
    }
    //json return
    @RequestMapping(value = "/json/{name}",method = RequestMethod.GET)
    public JsonVo testJsonData(@PathVariable("name")String name) {
        return new JsonVo(name);
    }
    //check header
    @RequestMapping(value = "/checkHeader",method = RequestMethod.GET)
    public ResponseEntity<String> checkHeader(String name, HttpServletRequest httpServletRequest) {
        log.info("Hello!!!!!!!! {}", name);
        if(!httpServletRequest.getHeader("Authentication").equals("LEMON")) {
            return new ResponseEntity<>("permission denied", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("welcome!", HttpStatus.OK);
    }
    //post
    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public ResponseEntity<String> postForEntity(@RequestBody Map<String,String> contents){
        log.info("requestbody: {}", contents.get("contents"));
        return new ResponseEntity<>("Success Response", HttpStatus.OK);
    }
}
