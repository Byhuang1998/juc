package com.byhuang.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 19:09
 * @description TODO
 */
public class SemaphoreDemo {

    public static final int SMP_NUM = 3;

    public static final int CAR_NUM = 7;

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(SMP_NUM);

        for (int i = 0; i < CAR_NUM; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("第" + Thread.currentThread().getName() + "辆车抢到了车位...");
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    System.out.println("第" + Thread.currentThread().getName() + "辆车离开了车位...");
                }
            }, String.valueOf(i)).start();
        }
    }
}
