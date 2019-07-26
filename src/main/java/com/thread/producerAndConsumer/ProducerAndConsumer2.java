/*package com.thread.producerAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

*//**
 * @author riemann
 * @date 2019/07/20 0:52
 *//*
public class ProducerAndConsumer2 {

    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 4; i++) {
            new Thread((new Produce(list)),"生产者线程" + i +" - produce: ").start();
            new Thread((new Consumer(list)),"消费者线程" + i +" - consume: ").start();
        }
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
                while (list.size() > 0) {
                    //因为生产者线程有多个，当本线程wait之后，假如一个生产者线程得到锁（本该消费者得到），
                    // 如果是if，那么此线程就会继续执行，会导致数据错乱。
                    //如果是while则会继续等待。
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
                list.notifyAll();//唤醒此对象锁所有等待线程（消费者和生产者线程均有）
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
                while (list.size() < 1) {
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
                list.notifyAll();//唤醒此对象锁所有等待线程（消费者和生产者线程均有）
            }
        }
    }

}*/
