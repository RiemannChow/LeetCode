package com.concurrent.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/07/28 23:41
 */
public class ExecutorServiceDemo1 {

    static Runnable run = () -> {
        try {
            Thread.sleep(5000);
            System.out.println("thread finish");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        service.execute(run);
        service.shutdown();
        service.execute(run);
    }

}


