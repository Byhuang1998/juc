package com.byhuang.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 16:47
 * @description CountDownLatch
 */
public class CountDownLatchDemo {

    public static final int STU_NUm = 6;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(STU_NUm);

        for (int i = 0; i < STU_NUm; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "号同学离开了教室...");
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println("所有同学离开了教室，" + Thread.currentThread().getName() + "锁门...");

    }
}
