/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author liguang
 * @version CompletableFutureDemo.java, v 0.1 2020年10月16日 20:14
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws InterruptedException {


        CompletableFuture
                .runAsync(() -> System.out.println("start..."))
                .thenRun(() -> System.out.println("startted!"))
                .exceptionally(throwable -> {
                    System.out.println(throwable);
                    return null;
                });


        TimeUnit.SECONDS.sleep(10);
    }
}