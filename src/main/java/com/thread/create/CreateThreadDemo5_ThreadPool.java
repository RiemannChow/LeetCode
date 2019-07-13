package com.thread.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/07/13 16:51
 */
public class CreateThreadDemo5_ThreadPool {

    public static void main(String[] args) {
        // 创建固定大小的线程池
        ExecutorService threadPool = Executors.newFixedThreadPool(8);

        while (true) {
            // 提交多个线程任务，并执行
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    printThreadInfo();
                }
            });
        }
    }

    private static void printThreadInfo() {
        System.out.println("当前运行的线程名为: " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
