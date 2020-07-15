package com.spring.ioc.Conversion;

import com.spring.ConversionService.ConversionController;
import com.spring.ConversionService.Convert.ConverterBean;
import com.spring.ConversionService.Formatter.FormatterBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

@WebMvcTest({ConversionController.class, ConverterBean.class, FormatterBean.class})
public class ConversionTest {

    @Autowired
    MockMvc bean;

    @Autowired
    WebApplicationContext ctx;

    @BeforeEach
    void setContext(){
        this.bean =  MockMvcBuilders.webAppContextSetup(ctx)
                .addFilter(new CharacterEncodingFilter("UTF-8",true))
                .build();
    }
    @Test
    void converterTest() throws Exception {
        String paramStr = "TEST|Converter";

        bean.perform(MockMvcRequestBuilders.get("/convert/"+paramStr)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(paramStr))
                .andDo(MockMvcResultHandlers.print());;

    }

    @Test
    void formatterTest( )throws Exception {
        String paramStr = "TEST|Converter";

        bean.perform(MockMvcRequestBuilders.get("/format/"+paramStr)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string(paramStr))
                .andDo(MockMvcResultHandlers.print());;
    }
}
