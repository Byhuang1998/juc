package com.byhuang.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 9:51
 * @description list线程不安全问题
 * java.util.ConcurrentModificationException
 */
public class ThreadDemo4 {

    public static void main(String[] args) {

//        List<String> list = new ArrayList<>();

        // solution 1
//        List<String> list = new Vector<>();

        // solution 2
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

        // solution 3 写时复制技术 Lock加锁，复制一份原List后，添加元素，覆盖原List
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "i").start();
        }
    }
}
