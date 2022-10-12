package com.byhuang.juc;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 17:11
 * @description TODO
 */
public class CyclicBarrierDemo {

    public static final int BARRIER_NUM = 7;

    public static void main(String[] args) {

        int[] arr = new int[BARRIER_NUM];

        CyclicBarrier cyclicBarrier = new CyclicBarrier(BARRIER_NUM, () -> {
            Thread.currentThread().setName("7");
            System.out.println("第" + Thread.currentThread().getName() + "颗龙珠已收集...");
            System.out.println(BARRIER_NUM + "颗龙珠已被全部收集...");
        });

        for (int i = 1; i < BARRIER_NUM; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("第" + Thread.currentThread().getName() + "颗龙珠已收集...");
                try {
                    arr[finalI] = finalI;
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        int sum = Arrays.stream(arr).sum();
        System.out.println("sum is: " + sum);
    }
}
