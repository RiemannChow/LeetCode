package com.reflect;

public class Animal {

    public String name = "Dog";
    private int age = 30;

    //默认无参构造函数
    public Animal() {
        System.out.println("Animal");
    }

    //带参数的构造函数
    public Animal(String name, int age) {
        System.out.println(name + "," + age);
    }

    //公开 方法  返回类型和参数均有
    public String sayName(String name) {
        return "Hello," + name;
    }

}
