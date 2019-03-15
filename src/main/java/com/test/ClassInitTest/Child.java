package com.test.ClassInitTest;

public class Child extends Parent {

    private static String staticStringInChild = initStaticStringInChild();

    private String stringInChild = initStringInChild();

    static {
        System.out.println("子类：静态代码块");
    }

    {
        System.out.println("子类：普通代码块");
    }

    public Child() {
        System.out.println("子类：构造方法");
    }

    private static String initStaticStringInChild() {
        System.out.println("子类：静态方法，被静态成员变量赋值调用。");
        return "initStaticStringInChild";
    }

    private String initStringInChild() {
        System.out.println("子类：普通成员方法，被普通成员变量赋值调用。");
        return "initStringInChild";
    }
}
