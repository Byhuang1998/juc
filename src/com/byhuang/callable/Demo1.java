package com.byhuang.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 16:07
 * @description compare callable and runnable
 */
public class Demo1 {

    public static void main(String[] args) {

        new Thread(new MyThread1(), "A").start();
        /*
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println(Thread.currentThread().getName() + ": implements Callable");
            return 1024;
        });
        */
        new Thread(new FutureTask<Integer>(() -> {
            System.out.println(Thread.currentThread().getName() + ": implements Callable");
            return 1024;
        }), "B").start();

    }

}

class MyThread1 implements Runnable {

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": implements Runnable");
    }
}

class MyThread2 implements Callable<Integer> {

    public Integer call() {
        return 1024;
    }
}
