package com.spring.Async.CompletableFutureTest;

import lombok.Data;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Data
public class Shop {
    private final String name;
    private final Random random ;

    public Shop(String name) {
        this.name = name;
        this.random = new Random(name.charAt(0)+name.charAt(1)+name.charAt(2));
    }

    public double getPrice(String product){
        return calculatePrice(product);
    }
    public String getPrice(String product,Discount.Code code){
        return String.join(":",product,String.valueOf(calculatePrice(product)),code.toString());
    }
    private double calculatePrice(String product){
        delay();
        return random.nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        int delay = 1000;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Future<Double> getAsyncPrice(String product){
//        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
//        new Thread(()->{
//            try {
//                double price = calculatePrice(product);
//                //계산을 비동기로 실행.
//                futurePrice.complete(price);
//            }catch (Exception e){
//                //예외 처리
//                futurePrice.completeExceptionally(e);
//            }
//        }).run();
//        return futurePrice;
//        ==(같음)
        //ForkJoinPool의 Executor 중 하나가 이 Supplier를 실행
        return CompletableFuture.supplyAsync(()->calculatePrice(product));
    }
}
