package com.concurrent.executorService.synRequest;

import java.util.concurrent.BlockingQueue;

/**
 * @author riemann
 * @date 2019/09/13 10:43
 */
public class Consumer implements Runnable {

    private final BlockingQueue queue;

    public Consumer(BlockingQueue q) {
        queue = q;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                consume(queue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consume(Object take) {
        System.out.println("consume" + take.toString());
    }
}
