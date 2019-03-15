package com.test;

public class SychronizedClassClass implements Runnable {
    static SychronizedClassClass instance1 = new SychronizedClassClass();
    static SychronizedClassClass instance2 = new SychronizedClassClass();

    @Override
    public void run() {
        method();
    }

    private void method() {
        synchronized (SychronizedClassClass.class) {
            System.out.println("我是类锁的第二种形式：Sychronized(*.class)。我叫 " + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println( Thread.currentThread().getName() + "运行结束。");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){

        }
        System.out.println("finished....");
    }
}
