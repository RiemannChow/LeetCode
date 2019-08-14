package com.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author riemann
 * @date 2019/08/03 17:35
 */
public class ABATest {

    private static AtomicInteger atomicInteger = new AtomicInteger(100);
    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<Integer>(100,0);

    public static void main(String[] args) throws InterruptedException {
        Thread intT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                atomicInteger.compareAndSet(100,101);
                atomicInteger.compareAndSet(101,100);
            }
        });

        Thread intT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean flag = atomicInteger.compareAndSet(100,101);// true
                System.out.println("flag is " + flag);
            }
        });

        intT1.start();
        intT2.start();
        intT1.join();
        intT2.join();

        Thread refT1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                atomicStampedRef.compareAndSet(100,101,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() + 1);
                atomicStampedRef.compareAndSet(101,100,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() + 1);
            }
        });

        Thread refT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = atomicStampedRef.getStamp();
                System.out.println("before sleep : stamp = " + stamp);
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after sleep : stamp = " + atomicStampedRef.getStamp());
                boolean flag = atomicStampedRef.compareAndSet(100,101,stamp,stamp + 1);
                System.out.println("thread refT2: " + atomicStampedRef.getReference() + ", flag is " + flag);
            }
        });
        refT1.start();
        refT2.start();
    }

}
