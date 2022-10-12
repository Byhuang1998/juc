package com.byhuang.readwrite;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 19:27
 * @description TODO
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            int finalI1 = i;
            new Thread(() -> {
                myCache.put(finalI, finalI1);
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 30; i++) {
            int finalI1 = i;
            new Thread(() -> {
                try {
                    myCache.get(finalI1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

    }
}

class MyCache {

    private volatile Map<Integer, Integer> map = new HashMap<>();

    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    private Lock rLock = rwLock.readLock();
    private Lock wLock = rwLock.writeLock();

    public void put(Integer K, Integer V) {
        wLock.lock();
        try {
            map.put(K, V);
            System.out.println(Thread.currentThread().getName() + "正在写...");
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wLock.unlock();
            System.out.println("写完了");
        }

    }

    public Integer get(Integer K) throws InterruptedException {
        rLock.lock();
        Integer res = null;
        try {
            res = map.get(K);
            System.out.println(Thread.currentThread().getName() + "正在读...");
            TimeUnit.SECONDS.sleep(1);
        } finally {
            rLock.unlock();
            System.out.println("读完了...");
        }
        return res;
    }


}
