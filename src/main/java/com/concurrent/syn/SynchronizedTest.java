package com.concurrent.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/08/11 23:40
 */
public class SynchronizedTest {

    public static SynchronizedTest staticIn = new SynchronizedTest();   //静态对象

    public static ReentrantLockTest staticIn2 = new ReentrantLockTest();   //静态对象2

    public synchronized void method1() {// 非静态方法1
        for (int i = 0; i < 10; i++) {
            System.out.println("method1 is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void method2() {// 非静态方法2
        for (int i = 0; i < 10; i++) {
            System.out.println("method2 is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void staticMethod1() {// 静态方法1
        for (int i = 0; i < 10; i++) {
            System.out.println("staticMethod1 is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void staticMethod2() {// 静态方法2
        for (int i = 0; i < 10; i++) {
            System.out.println("staticMethod2 is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Thread1 implements Runnable {

        @Override
        public void run() {
            SynchronizedTest.staticIn.method2();
        }

    }

    static class Thread2 implements Runnable {

        @Override
        public void run() {
            staticIn2.staticMethod2();
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Thread1());
        Thread t2 = new Thread(new Thread2());
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(t1);
        service.execute(t2);
        service.shutdown();
    }


}
