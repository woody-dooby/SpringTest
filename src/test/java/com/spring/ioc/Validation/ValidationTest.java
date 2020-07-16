package com.spring.ioc.Validation;

import com.spring.Validation.CustomValidator;
import com.spring.Validation.ValidatorControllers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;

@WebMvcTest({ValidatorControllers.class, CustomValidator.class})
public class ValidationTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    WebApplicationContext ctx;

    @BeforeEach
    void init(){
        mvc = MockMvcBuilders.webAppContextSetup(ctx).addFilter(new CharacterEncodingFilter("UTF-8",true)).build();
    }

    @Test
    void methodValid() throws Exception {
        String params = "type=ERROR&name=TESTER";
        mvc.perform(MockMvcRequestBuilders.get("/valid/method?"+params)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void annotationValid() throws Exception {
        String params = "type=ERROR&name=TESTER";
        mvc.perform(MockMvcRequestBuilders.get("/valid/annotation?"+params)
                .characterEncoding(StandardCharsets.UTF_8.name())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().string("error"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void jsr303Valid() throws Exception {
        String params = "type=ERROR&name=TESTER";
        try {
            final ResultActions actions =  mvc.perform(MockMvcRequestBuilders.get("/valid/JSR303?"+params)
                    .characterEncoding(StandardCharsets.UTF_8.name())
                    .contentType(MediaType.APPLICATION_JSON));

            actions.andExpect(MockMvcResultMatchers.status().isExpectationFailed());
        }catch (Exception e){
            Assertions.assertTrue(e.getCause().getCause() instanceof NullPointerException);
        }

    }
}
