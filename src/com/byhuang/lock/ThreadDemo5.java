package com.byhuang.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 9:51
 * @description list线程不安全问题
 * java.util.ConcurrentModificationException
 */
public class ThreadDemo5 {

    public static void main(String[] args) {

//        Set<String> set = new HashSet<>();

        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "i").start();
        }
    }
}
