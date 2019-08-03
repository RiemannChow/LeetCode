package com.concurrent.syn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/08/01 23:30
 */
public class PrintLetter1 {

    static Object syn = new Object();
    public static String next = "a";
    volatile int count = 10;
    public static void main(String[] args) {
        new PrintLetter1().print();
    }

    private void print() {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.execute(new APrinThread());
        service.execute(new BPrinThread());
        service.execute(new CPrinThread());
    }

    class APrinThread implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                synchronized (syn) {
                    while (!"a".equals(next)) {
                        try {
                            syn.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("a");
                    next = "b";
                    syn.notifyAll();
                    count--;
                }
            }
        }

    }

    class BPrinThread implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                synchronized (syn) {
                    while (!"b".equals(next)) {
                        try {
                            syn.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("b");
                    next = "c";
                    syn.notifyAll();
                    count--;
                }
            }
        }

    }

    class CPrinThread implements Runnable {

        @Override
        public void run() {
            while (count > 0) {
                synchronized (syn) {
                    while (!"c".equals(next)) {
                        try {
                            syn.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print("c");
                    next = "a";
                    syn.notifyAll();
                    count--;
                }
            }
        }

    }

}
