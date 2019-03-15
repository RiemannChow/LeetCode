package com.thread;

class RunableDemo implements Runnable{
    private Thread t;
    private String threadName;

    RunableDemo(String name){
        threadName = name;
        System.out.println("Creating " + threadName);
    }

    public void run(){
        for (int i = 4; i >0; i--){
            System.out.println("Thread: " + threadName + ", " + i);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }

    public static void main(String[] args) {
        RunableDemo R1 = new RunableDemo("Thread-1");
        R1.start();

        RunableDemo R2 = new RunableDemo("Thread-2");
        R2.start();
    }

}



