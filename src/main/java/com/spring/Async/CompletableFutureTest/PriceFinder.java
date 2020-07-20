package com.spring.Async.CompletableFutureTest;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class PriceFinder {
    private final List<Shop> shops = Arrays.asList(
            new Shop("CoolPang"),
            new Shop("HMarket"),
            new Shop("12th Street"),
            new Shop("YouMakePrice"),
            new Shop("FBay"));

    public List<String> findPrices(String product) {
        return shops.stream()
                .map(shop -> String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    public List<String> findPrices2(String product) {
        return shops.parallelStream()
                .map(shop -> String.format("%s 가격은 %.2f", shop.getName(), shop.getPrice(product)))
                .collect(Collectors.toList());
    }
    public List<String> findPrices3(String product) {
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName());
                    return String.format("%s 가격은 %.2f",shop.getName(),shop.getPrice(product)); })
                ).collect(Collectors.toList());
        // CompletableFuture의 join 은 Future의 get과 비슷 --> 예외 처리가 되어있음.
        // 코드의 첫 문단에서 stream을 열었고 두 번째 return 문단에서도 stream을 열었습니다.
        // 스트림의 게으른 특성 때문에 순차적으로 계산이 되기 때문에 속도가 원하는 만큼 빨라지지 않습니다.
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    public List<String> findPrices4(String product) {
        // 성능의 향상을 위하여 Executor 를 생성하여 전달한다.
        // CompletableFuture 의 ForkJoinPool.commonPool 을 대체하는 ThreadTaskExecutor 로 사용하여 속도 향샹
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(r)->{Thread t = new Thread(r); t.setDaemon(true); return t;});

        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName());
                    return String.format("%s 가격은 %.2f",shop.getName(),shop.getPrice(product));
                    },executor )
                ).collect(Collectors.toList());
        return priceFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
    public List<String> findPrices5(String product,Discount.Code code) {
        return shops.stream()
                .map(shop ->Quote.parse(String.join(":",shop.getName(),String.valueOf(shop.getPrice(product)),code.toString())))
                .map(Discount::applyDiscount)
                .collect(Collectors.toList());
    }
    public List<String> findPrices6(String product,Discount.Code code) {
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(),100),(r)->{Thread t = new Thread(r); t.setDaemon(true); return t;});

        //Quote 의 동기적 실행때문에 동기 + 비동기적방법이다.
        List<CompletableFuture<String>> priceFutures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(()->shop.getPrice(product, code), executor))
                // thenApply : 앞선 supplyAsync 작업이 종료됨을 기달리고 수행되는 작업을 사용할때 사용한다.
                .map(future->future.thenApply(Quote::parse))
                // thenCompose : 변수 `Fnction<? super T, ? extends CompletionStage<U>> fn`를 받는걸로 유추 할 수 있듯이 처음의 인자를 받아 CompletionStage 를 상속받는 Function 작업을 해야한다.
                // 결론적으로, 가지고 있는 Future 인자를 새로운 CompletableFuture의 인자로써 사용할 수 있게 도와준다.
                .map(future->future.thenCompose(quote -> CompletableFuture.supplyAsync(()->Discount.applyDiscount(quote),executor) ) )
                .collect(Collectors.toList());

        return priceFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
