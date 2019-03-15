/*
package com.thread;

public class SynAndAsynDemo {
    public static void main(String[] args) {
        final SynAndAsynDemo mo = new SynAndAsynDemo();
        new Thread(new Runable() {
            public void run() {
                mo.methodA();
            }
        }).start();

        new Thread(new Runable() {
            public void run() {
                mo.methodB();
            }
        }).start();

//        t1.start();
//        t2.start();
    }

    public static synchronized void methodA() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void methodB() {
        System.out.println(Thread.currentThread().getName());

    }
}
*/
