package com.jvm;

/**
 * 方法静态解析
 */
public class StaticResolution {
    public static void main(String[] args) {
        StaticResolution.sayHello();
    }

    private static void sayHello() {
        System.out.println("hello jvm");
    }
}
