package com.byhuang.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/11 20:33
 * @description TODO
 */
public class ThreadDemo3 {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareResource.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();
    }
}

class ShareResource {

    private int flag = 1;

    private Lock lock = new ReentrantLock();

    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() throws InterruptedException {

        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                c1.await();
            }
            // 干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            // 通知
            flag = 2;
            c2.signal();
        } finally {
            lock.unlock();
        }

    }
    public void print10() throws InterruptedException {

        lock.lock();
        try {
            while (flag != 2) {
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            flag = 3;
            c3.signal();
        } finally {
            lock.unlock();
        }
    }
    public void print15() throws InterruptedException {

        lock.lock();
        try {
            while (flag != 3) {
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            flag = 1;
            c1.signal();
        } finally {
            lock.unlock();
        }
    }
}
