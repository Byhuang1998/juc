package com.byhuang.sync;

/**
 * @author mskj-huangbingyi
 * @version 1.0
 * @data 2022/10/10 18:40
 * @description TODO
 */
public class SaleTicket {

    public static void main(String[] args) {
        // 第二步，创建多个线程模拟多个售票窗口
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // 调用卖票方法
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();

    }

}

/**
* @author mskj-huangbingyi
* @date: 2022/10/10 18:45
* @param:
* @return:
* @description: 创建资源类
*/
class Ticket {

    /**
     * 票数
     */
    private int number = 30;

    // 操作：卖票
    public synchronized void sale() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "：卖出：" + number-- + "剩下：" + number);
        }
    }
}
