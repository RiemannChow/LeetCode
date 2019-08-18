package com.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author riemann
 * @date 2019/08/12 22:23
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final int num = 5;
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(num);

        for (int i = 0; i < num; i++) {
            new Thread(new AWork(i, begin, end)).start();
        }

        // judge prepare...
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("judge say : run !");
        begin.countDown();
        long startTime = System.currentTimeMillis();

        try {
            end.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            long endTime = System.currentTimeMillis();
            System.out.println("judge say : all arrived !");
            System.out.println("spend time: " + (endTime - startTime));
        }
    }

}

class AWork implements Runnable {

    final CountDownLatch begin;
    final CountDownLatch end;
    final int id;

    public AWork(final int id, final CountDownLatch begin, final CountDownLatch end) {
        this.id = id;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.id + " ready !");
            begin.await();
            // run...
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.id + " arrived !");
            end.countDown();
        }
    }

}
