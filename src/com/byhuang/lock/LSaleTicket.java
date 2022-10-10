package com.byhuang.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @data 2022/10/10 19:22
 * @description TODO
 */
public class LSaleTicket {


    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

class LTicket {

    private int number = 30;

   private final ReentrantLock lock = new ReentrantLock();

    public void sale() {
        // 上锁
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "：卖出：" + number-- + "剩下：" + number);
            }
        } catch (Exception e) {

        } finally {
            // 解锁
            lock.unlock();
        }
    }
}
