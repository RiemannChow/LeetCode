package com.concurrent.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/07/29 0:12
 */
public class ExecutorServiceDemo4 {

    static Runnable run = () -> {
        long num = 0;
        while (true && !Thread.currentThread().isInterrupted()) {
            num += 1;
        }
        System.out.println(num);
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(run);
        service.shutdownNow();
    }

}
