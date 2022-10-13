package com.byhuang.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/13 11:27
 * @description TODO
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Asynchronous call has no return value
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture1");
        });
        completableFuture1.get();

        // Asynchronous call has return value
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "completableFuture2");
//            int a = 1 / 0;
            return 1024;
        });
        completableFuture2.whenComplete((u, v) -> {
            System.out.println("res: " + u);
            System.out.println("exception: " + v);
        }).get();
    }
}
