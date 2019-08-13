package com.concurrent.resume;

/**
 * @author riemann
 * @date 2019/08/03 23:22
 */
public class DeprecatedSuspendResume extends Object implements Runnable {

    //volatile关键字，表示该变量可能在被一个线程使用的同时，被另一个线程修改
    private volatile int firstVal;
    private volatile int secondVal;

    //判断二者是否相等
    public boolean valuesAreEqual() {
        return (firstVal == secondVal);
    }

    public void run() {
        firstVal = 0;
        secondVal = 0;
        workMethod();
    }

    private void workMethod() {
        int val = 1;
        while (true) {
            stepOne(val);
            stepTwo(val);
            val++;
            try {
                Thread.sleep(200);//再次循环钱休眠200ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //赋值后，休眠300毫秒，从而使线程有机会在stepOne操作和stepTwo操作之间被挂起
    private void stepOne(int newVal) {
        firstVal = newVal;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stepTwo(int newVal) {
        secondVal = newVal;
    }

    public static void main(String[] args) {
        DeprecatedSuspendResume dsr = new DeprecatedSuspendResume();
        Thread thread = new Thread(dsr);
        thread.start();

        //休眠1秒，让其他线程有机会获得执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            //挂起线程
            thread.suspend();
            System.out.println("dsr.valuesAreEqual() = " + dsr.valuesAreEqual());
            //恢复线程
            thread.resume();
            try {
                //线程随机休眠0~2秒
                Thread.sleep((long) (Math.random() * 2000.0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);//中断应用程序
    }

}
