package com.concurrent.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/07/29 0:05
 */
public class ExecutorServiceDemo3 {

    static Runnable run = () -> {
        long num = 0;
        boolean flag = true;
        while (flag) {
            num += 1;
            if (num == Long.MAX_VALUE) {
                flag = false;
            }
        }
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(1);
        service.execute(run);
        service.shutdownNow();
    }

}
