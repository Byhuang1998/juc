package com.byhuang.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/12 23:06
 * @description TODO
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));

        System.out.println(blockingQueue.element());
    }
}
