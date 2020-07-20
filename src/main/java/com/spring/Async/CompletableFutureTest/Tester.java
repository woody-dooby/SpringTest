package com.spring.Async.CompletableFutureTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Future;

/*
* CompletableFuture
* 출처 : https://pjh3749.tistory.com/280
* */
@Slf4j
public class Tester {
    public static void main(String[] args) {
//        getAsyncPrice();
//        getStreamPrice();                           //5초
//        getParallelStreamPrice();                   //2초
//        getCompletableFutureStreamPrice();          //2초
//        getCompletableFutureExecutorStreamPrice();   //1초
//        getDiscountStreamPrice();                   //10초
        getDiscountCompletableFutureExecutorStreamPrice(); //2초

    }
    private static void getAsyncPrice(){
        Shop shop = new Shop("J Shop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getAsyncPrice("J Shop");
        try {
            log.info("Price is {}" ,futurePrice.get());
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
        }
    }
    private static void getStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices("Mac").forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
    private static void getParallelStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices2("Mac").forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
    private static void getCompletableFutureStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices3("Mac").forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
    private static void getCompletableFutureExecutorStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices4("Mac").forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
    private static void getDiscountStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices5("Mac",Discount.Code.DIAMONE).forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
    private static void getDiscountCompletableFutureExecutorStreamPrice() {
        PriceFinder priceFinder = new PriceFinder();
        long start = System.nanoTime();
        priceFinder.findPrices6("Mac",Discount.Code.DIAMONE).forEach(log::info);

        log.info("Time : {}",(System.nanoTime() - start)/1_000_000);
    }
}
