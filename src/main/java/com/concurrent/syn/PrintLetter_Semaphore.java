package com.concurrent.syn;

import java.util.concurrent.Semaphore;

/**
 * @author riemann
 * @date 2019/08/03 0:54
 */
public class PrintLetter_Semaphore {

    // 以a开始的信号量,初始信号量数量为1
    private static Semaphore a = new Semaphore(1);
    // b、c信号量,a完成后开始,初始信号数量为0
    private static Semaphore b = new Semaphore(0);
    private static Semaphore c = new Semaphore(0);
    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    a.acquire();// a获取信号执行,a信号量减1,当a为0时将无法继续获得该信号量
                    System.out.print("a");
                    b.release();// b释放信号，b信号量加1（初始为0），此时可以获取b信号量
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    b.acquire();
                    System.out.print("b");
                    c.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    c.acquire();
                    System.out.print("c");
                    a.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }

}
