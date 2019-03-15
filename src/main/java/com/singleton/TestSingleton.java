package com.singleton;

public class TestSingleton {

    String name = null;

    private TestSingleton() {}

    private static volatile TestSingleton instance = null;

    public static TestSingleton getInstance() {
        if (instance == null) {
            synchronized (TestSingleton.class) {
                if (instance == null) {
                    instance = new TestSingleton();
                }
            }
        }
        return instance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo() {
        System.out.println("The name is " + name);
    }

    public static void main(String[] args) {

        TestSingleton ts1 = TestSingleton.getInstance();
        ts1.setName("edgar");
        TestSingleton ts2 = TestSingleton.getInstance();
        ts2.setName("riemann");

        ts1.printInfo();
        ts2.printInfo();

        if (ts1 == ts2) {
            System.out.println("创建的是同一个实例");
        } else {
            System.out.println("创建的不是同一个实例");
        }

    }
}
