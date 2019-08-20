package com.concurrent;

/**
 * @author riemann
 * @date 2019/08/03 15:35
 */
public class WaitNotifyTest {

    // 在多线程间共享的对象上使用wait
    private String[] shareObj = {"true"};

    public static void main(String[] args) {
        WaitNotifyTest wnTest = new WaitNotifyTest();
        ThreadWait threadWait1 = wnTest.new ThreadWait("wait thread1");
        ThreadWait threadWait2 = wnTest.new ThreadWait("wait thread2");
        ThreadWait threadWait3 = wnTest.new ThreadWait("wait thread3");
        threadWait1.setPriority(1);
        threadWait2.setPriority(2);
        threadWait3.setPriority(3);

        ThreadNotify threadNotify = wnTest.new ThreadNotify("notify thread");
        threadNotify.start();
        threadWait1.start();
        threadWait2.start();
        threadWait3.start();
    }

    class ThreadWait extends Thread {

        public ThreadWait(String name) {
            super(name);
        }

        public void run() {
            synchronized (shareObj) {
                while ("true".equals(shareObj[0])) {
                    System.out.println(this.getName() + " 开始等待");
                    long startTime = System.currentTimeMillis();
                    try {
                        shareObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println(this.getName() + " 等待时间为: " + (endTime - startTime));
                }
            }
            System.out.println(getName() + " 等待结束");
        }

    }

    class ThreadNotify extends Thread {

        public ThreadNotify(String name) {
            super(name);
        }

        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj) {
                System.out.println(this.getName() + " 开始准备通知");
                shareObj[0] = "false";
                shareObj.notifyAll();
                System.out.println(this.getName() + " 通知结束");
            }
            System.out.println(this.getName() + " 运行结束");
        }
    }

}
