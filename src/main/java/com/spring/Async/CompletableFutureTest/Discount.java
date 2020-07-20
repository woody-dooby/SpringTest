package com.spring.Async.CompletableFutureTest;

import lombok.RequiredArgsConstructor;

public class Discount {

    @RequiredArgsConstructor
    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMONE(20);

        private final int percentage;
    }
    public static String applyDiscount(Quote quote){
        return quote.getShopName() + " price is "+ apply(quote.getPrice(),quote.getDiscountCode());
    }
    private static double apply(double price, Code code){
        delay();
        return (price * (100 - code.percentage) / 100);
    }

    private static void delay() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
