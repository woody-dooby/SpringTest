package com.spring.SpEL;

import lombok.extern.slf4j.Slf4j;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

@Slf4j
public class SpELparser {

    private final ExpressionParser parser = new SpelExpressionParser();

    public void setExpressions(String expressionMsg, SpELType spELType){
        Expression expression = null;
        if(expressionMsg.contains("#")){
            expression = parser.parseExpression(expressionMsg, ParserContext.TEMPLATE_EXPRESSION);
        }else{
            expression = parser.parseExpression(expressionMsg);
        }

        log.debug(spELType.getValue(expression).toString());
    }
    @FunctionalInterface
    private interface SpELType<T>{
        T getValue(Expression expression);
    }
    public class Literal implements SpELType<String>{
        @Override
        public String getValue(Expression expression) {
            return expression.getValue(String.class);
        }
    }
    public class Method implements SpELType{
        @Override
        public Object getValue(Expression expression) {
            return expression.getValue();
        }
    }
    public class Mathematical implements SpELType{
        @Override
        public Object getValue(Expression expression) {
            return expression.getValue();
        }
    }
    public class Property implements SpELType{
        @Override
        public Object getValue(Expression expression) {
            EvaluationContext itemContext = new StandardEvaluationContext(System.getProperties());
            return expression.getValue(itemContext);
        }
    }
}

