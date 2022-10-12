package com.byhuang.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 9:51
 * @description list线程不安全问题
 * java.util.ConcurrentModificationException
 */
public class ThreadDemo4 {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "i").start();
        }
    }
}
