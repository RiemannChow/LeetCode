package com.thread.create;

/**
 * @author riemann
 * @date 2019/07/13 16:10
 */
public class CreateThreadDemo3_Main {

    public static void main(String[] args) {
        // 实例化线程任务类
        CreateThreadDemo3_Task task = new CreateThreadDemo3_Task();

        // 创建线程对象,并将线程任务类作为构造方法参数传入
        new Thread(task).start();

        // 主线程的任务，为了演示多个线程一起执行
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
