package com.concurrent.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author riemann
 * @date 2019/07/27 22:51
 */
public class CyclicBarrierDemo {

    static Integer count = 0;

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            if (count == 0) {
                System.out.println("班车准备开始运营！");
                count++;
            } else {
                System.out.println("车上座位已满，请等待下一班！");
                count++;
            }
        });
        //公寓有一百人
        for (int i = 0; i < 100 ; i++) {
            new Thread(() -> {
                try {
                    //模拟起床耗时
                    Thread.sleep((long) (Math.random() * 1000));
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + " 赶上了" + count + "趟班车。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
