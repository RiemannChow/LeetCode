package com.thread;

class ThreadDemo extends Thread {
    private Thread thread;
    private String threadName;

    ThreadDemo(String name) {
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    @Override
    public void run() {
        System.out.println("Running " + threadName);

        for (int i = 4; i > 0; i--) {
            System.out.println("Thread: " + threadName + "," + i);
            // 让线程睡眠一会
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                System.out.println("Thread " +  threadName + " interrupted.");
            }
            System.out.println("Thread " +  threadName + " exiting.");
        }

    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public class TestThread {
        public void main(String[] args) {
            Thread t1 = new Thread("Thread-1");
            t1.start();

            Thread t2 = new Thread("Thread-2");
            t2.start();
        }
    }
}
