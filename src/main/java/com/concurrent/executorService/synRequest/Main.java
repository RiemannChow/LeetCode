package com.concurrent.executorService.synRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author riemann
 * @date 2019/09/13 10:55
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>(5);

        Producer p = new Producer(queue);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);

        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

}
