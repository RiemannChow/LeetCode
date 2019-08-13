package com.concurrent.syn;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author riemann
 * @date 2019/08/03 0:28
 */
public class ReentrantLockTest {

    private ReentrantLock lock = new ReentrantLock();
    public void testReentrantLock() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " get lock");
            long beginTime = System.currentTimeMillis();
            while (System.currentTimeMillis() - beginTime < 100) {

            }
            //线程再次获得该锁（可重入）
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " get lock again");
                long beginTime2 = System.currentTimeMillis();
                while (System.currentTimeMillis() - beginTime2 < 100) {

                }
            } finally {
                // 线程第一次释放锁
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release lock");
            }

        } finally {
            // 线程再次释放锁
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock again");
        }
    }

    public synchronized void method1() {// 非静态方法1
        for (int i = 0; i < 10; i++) {
            System.out.println("method1 concurrent is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void staticMethod2() {// 静态方法2
        for (int i = 0; i < 10; i++) {
            System.out.println("staticMethod2 concurrent is running!");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final ReentrantLockTest test = new ReentrantLockTest();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                test.testReentrantLock();
            }
        },"a");
        thread.start();
    }

}
