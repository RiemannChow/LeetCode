package com.concurrent.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author riemann
 * @date 2019/07/27 23:49
 */
public class ExchangerDemo {

    private static final Exchanger<String> exchanger = new Exchanger<String>();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String A = "银行流水100";// A录入银行流水数据
                    String B = exchanger.exchange(A);
                    System.out.println("A的视角：A和B数据是否一致: " + A.equals(B) +
                            "，A录入的是: " + A + "，B录入是: " + B + "。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String B = "银行流水200";// B录入银行流水数据
                    String A = exchanger.exchange(B);
                    System.out.println("B的视角：A和B数据是否一致: " + A.equals(B) +
                            "，A录入的是: " + A + "，B录入是: " + B + "。");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
