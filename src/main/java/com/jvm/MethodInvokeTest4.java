package com.jvm;

import java.lang.reflect.Method;

// 在运行指令中添加如下两个虚拟机参数：
// -Djava.lang.Integer.IntegerCache.high=128
// -Dsun.reflect.noInflation=true
public class MethodInvokeTest4 {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.jvm.MethodInvokeTest4");
        Method method = clazz.getMethod("target", int.class);

        method.setAccessible(true);  // 关闭权限检查

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
