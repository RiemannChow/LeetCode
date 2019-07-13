package com.thread.create;

/**
 * @author riemann
 * @date 2019/07/13 16:07
 */
public class CreateThreadDemo3_Task implements Runnable {

    @Override
    public void run() {
        // 每隔1s中输出一次当前线程的名字
        while (true) {
            printThreadInfo();
            commonThreadSleep();
        }
    }

    public static void commonThreadSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadInfo() {
        System.out.println("当前运行的线程名为： " + Thread.currentThread().getName());
    }

}
