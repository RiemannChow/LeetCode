package com.concurrent;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author riemann
 * @date 2019/09/01 1:38
 */
public class DelayQueueTest {

    public static void main(String[] args) {

        DelayQueue<Message> queue = new DelayQueue<>();

        long now = System.currentTimeMillis();

        // 启动一个线程从队列中取元素
        new Thread(()->{
            while (true) {
                try {
                    // 将依次打印1000，2000，5000，7000，8000
                    System.out.println(queue.take().deadline - now);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 添加5个元素到队列中
        queue.add(new Message(now + 5000));
        queue.add(new Message(now + 8000));
        queue.add(new Message(now + 2000));
        queue.add(new Message(now + 1000));
        queue.add(new Message(now + 7000));

    }

}

class Message implements Delayed {

    long deadline;

    public Message(long deadline) {
        this.deadline = deadline;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return deadline - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "Message{" +
                "deadline=" + deadline +
                '}';
    }

}
