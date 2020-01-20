package com.jvm;

import java.lang.reflect.Method;

public class MethodInvokeTest2 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.jvm.MethodInvokeTest2");
        Method method = clazz.getMethod("target", int.class);

        long current = System.currentTimeMillis();
        for (int i = 0; i < 2_000_000_000; i++) {
            if (i % 100_000_000 == 0) {
                long temp = System.currentTimeMillis();
                System.out.println(temp - current);
                current = temp;
            }
            method.invoke(null, 128);
        }
    }

    public static void target(int i) {
        // empty method
    }
}
