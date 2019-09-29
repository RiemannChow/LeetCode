package com.concurrent.executorService.synRequest;

import java.util.concurrent.BlockingQueue;

/**
 * @author riemann
 * @date 2019/09/13 10:48
 */
public class Producer implements Runnable {

    private final BlockingQueue queue;

    public Producer(BlockingQueue q) {
        queue = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                queue.put(produce());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String produce() {
        String temp = (char)('A' + (int)(Math.random() * 26)) + "";
        System.out.println("produce" + temp);
        return temp;
    }
}
