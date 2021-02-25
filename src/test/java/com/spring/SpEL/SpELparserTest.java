package com.spring.SpEL;

import org.junit.jupiter.api.Test;

class SpELparserTest {

    @Test
    void PropertyTest(){
        System.setProperty("TEST","테스트값");
        SpELparser spELparser = new SpELparser();

        spELparser.setExpressions("output  #{TEST} ",
                spELparser.new Property());
    }
}
