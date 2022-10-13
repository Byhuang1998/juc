package com.byhuang.forkjoin;

import java.util.concurrent.*;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @date 2022/10/13 10:38
 * @description TODO
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task(1, 100);
        // create a ForkJoinPool object
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(task);
        // get result of fork and join
        int res = forkJoinTask.get();
        System.out.println(res);
        forkJoinPool.shutdown();
    }
}

class Task extends RecursiveTask<Integer> {

    private int start;
    private int end;

    Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end < start) {
            int tem = start;
            start = end;
            end = tem;
        }

        if (end - start <= 10) {
            int sum = 0;
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }

        int mid = (start + end) >> 1;

        Task subLeftTask = new Task(start, mid);
        Task subRightTask = new Task(mid + 1, end);
        subLeftTask.fork();
        subRightTask.fork();

        return subLeftTask.join() + subRightTask.join();

    }
}
