package com.byhuang.sync;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 11:13
 * @description TODO
 */
public class DeadLock {

    public static void main(String[] args) {

        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            synchronized (a) {
                System.out.println(Thread.currentThread().getName() + "获取到了锁a，试图获取锁b");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b) {
                    System.out.println(Thread.currentThread().getName() + "获取到了锁b");
                }
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + "获取到了锁b，试图获取锁a");
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (a) {
                    System.out.println(Thread.currentThread().getName() + "获取到了锁a");
                }
            }
        }, "B").start();
    }
}
