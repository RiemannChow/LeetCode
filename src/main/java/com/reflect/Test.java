package com.reflect;

public class Test {
    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Animal("cat", 20);
        System.out.println("name = " + a1.name);
        a2.name = "哈士奇";
        System.out.println("name修改后的值= " + a2.name);
        System.out.println(a2.sayName("riemann"));

    }
}
