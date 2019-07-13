package com.thread.create;

/**
 * @author riemann
 * @date 2019/07/13 15:48
 */
public class CreateThreadDemo2 extends Thread {

    public CreateThreadDemo2(String name) {
        // 设置当前线程的名字
        this.setName(name);
    }

    public void run() {
        // 每隔1s中输出一次当前线程的名字
        while (true) {
            // 输出线程的名字，与主线程名称相区分
            printThreadInfo();
            commonThreadSleep();
        }
    }

    public static void main(String[] args) {
        // 注意这里，要调用start方法才能启动线程，不能调用run方法
        new CreateThreadDemo2("MyThread-01").start();
        new CreateThreadDemo2("MyThread-02").start();

        // 演示主线程继续向下执行
        while (true) {
            printThreadInfo();
            commonThreadSleep();
        }
    }

    private static void commonThreadSleep() {
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
