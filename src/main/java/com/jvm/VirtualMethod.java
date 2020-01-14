package com.jvm;

/**
 * 虚方法调用
 */
public class VirtualMethod {

    static abstract class Human {
        abstract void sayHello();
        @Override
        public String toString() {
            return super.toString();
        }
    }

    static class Man extends Human {
        @Override
        void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        void sayHello() {
            System.out.println("woman say hello");
        }

        void childbirth() {
            System.out.println("woman childbirth");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        ((Woman) woman).childbirth();
    }
}
