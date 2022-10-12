package com.byhuang.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 9:51
 * @description list线程不安全问题
 * java.util.ConcurrentModificationException
 */
public class ThreadDemo6 {

    public static void main(String[] args) {

//        Map<Integer, String> map = new HashMap<>();

        Map<Integer, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(finalI, UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "i").start();
        }
    }
}
