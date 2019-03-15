package com.singleton;

//饿汉式单例类.在类初始化时，已经自行实例化
public class Singleton2 {
    private Singleton2() {}
    private static final Singleton2 singleton2 = new Singleton2();
    //静态工厂方法
    public static Singleton2 getInstance() {
        return singleton2;
    }
}
