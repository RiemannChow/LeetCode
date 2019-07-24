/*package com.thread.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

*//**
 * @author riemann
 * @date 2019/07/20 0:13
 *//*
public class ProducerAndConsumer {

    public static void main(String[] args) {
        List list = new ArrayList();
        new Thread((new Produce(list)),"生产者线程produce: ").start();
        new Thread((new Consumer(list)),"消费者线程consume: ").start();
    }

}

*//**
 * 生产者线程
 *//*
class Produce implements Runnable {

    private List list;

    public Produce(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            Random random = new Random();
            synchronized (list) {
                //可以是while
                if (list.size() > 0) {//表明集合中有元素，此线程等待
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.add(random.nextInt(100));//0-99的随机数
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + list.get(0));
                list.notify();//通知消费者，集合中已有元素。
            }
        }
    }

}

*//**
 * 消费者线程
 *//*
class Consumer implements Runnable {

    private List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                //可以是while
                if (list.size() < 1) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " " + list.remove(0));
                list.notify();
            }
        }
    }
}*/
