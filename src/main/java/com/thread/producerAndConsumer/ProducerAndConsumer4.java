package com.thread.producerAndConsumer;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author riemann
 * @date 2019/07/20 1:59
 */
public class ProducerAndConsumer4 {

    public static void main(String[] args) {
        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<>();
        ReentrantLock put = new ReentrantLock();
        Condition notFull = put.newCondition();
        ReentrantLock out = new ReentrantLock();
        Condition notEmpty = out.newCondition();
        for (int i = 0; i < 4; i++) {
            new Thread(new Producer(put, out ,notFull, notEmpty, queue),"生产者线程" + i +" - produce: ").start();
            new Thread(new Consumer(put, out ,notFull, notEmpty, queue),"消费者线程" + i +" - consume: ").start();
        }
    }

}

/**
 * 生产者
 */
class Producer implements Runnable {

    ReentrantLock put;
    ReentrantLock out;
    Condition notFull;
    Condition notEmpty;
    Queue<Integer> queue;

    public Producer(ReentrantLock put, ReentrantLock out, Condition notFull, Condition notEmpty, Queue<Integer> queue) {
        this.put = put;
        this.out = out;
        this.notFull = notFull;
        this.notEmpty = notEmpty;
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            put.lock();
            while (queue.size() == 10) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Integer integer = random.nextInt(100);
            queue.add(integer);
            System.out.println(Thread.currentThread().getName() + " " + integer +", 当前Queue还剩" + queue.size());
            //队列没有满，通知更多生产者来生产
            if (queue.size() < 10) {
                notFull.signal();
            }
            put.unlock();
            // 当队列已满时才通知消费者进行消费
            if (queue.size() == 10) {
                out.lock();
                notEmpty.signal();
                out.unlock();
            }
        }
    }

}

/**
 * 消费者
 */
class Consumer implements Runnable {

    ReentrantLock put;
    ReentrantLock out;
    Condition notFull;
    Condition notEmpty;
    Queue<Integer> queue;
    public Consumer(ReentrantLock put, ReentrantLock out, Condition notFull, Condition notEmpty, Queue queue){
        this.put=put;
        this.out=out;
        this.notFull=notFull;
        this.notEmpty=notEmpty;
        this.queue=queue;
    }

    @Override
    public void run() {
        while (true) {
            out.lock();
            while (queue.size() == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " "+ queue.poll() + ", " + "当前Queue还剩" + queue.size());
            //只要队列里还有元素，就通知更多消费者来消费
            if (queue.size() > 0) {
                notEmpty.signal();
            }
            out.unlock();
            //当队列为空时，通知生产者进行生产
            if (queue.size() == 0) {
                put.lock();
                notFull.signal();
                put.unlock();
            }
        }
    }

}
