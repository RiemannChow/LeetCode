package com.concurrent.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author riemann
 * @date 2019/07/29 0:17
 */
public class ExecutorServiceDemo5 {

    static Runnable run = () -> {
        long num = 0;
        boolean flag = true;
        while (flag && !Thread.currentThread().isInterrupted()) {
            num += 1;
            if (num == Long.MAX_VALUE) {
                flag = false;
            }
        }
        System.out.println(num);
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(run);
        service.shutdown();
        try {
            if (!service.awaitTermination(2, TimeUnit.SECONDS)) {
                service.shutdownNow();
            }
        } catch (InterruptedException e) {
            service.shutdownNow();
        }
    }

}
