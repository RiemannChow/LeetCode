package com.test;

public class TestFinal {
    public static void main(String[] args) {
        TestFinal testFinal = new TestFinal();
        StringBuffer sb = new StringBuffer("hello");
        testFinal.changeValue(sb);
        System.out.println("main方法里的 ：" + sb);
    }

    public void changeValue(StringBuffer sb) {
        //sb重新指向另一个对象
        sb = new StringBuffer("say");
        sb.append("world");
        System.out.println("changeValue方法里的 ：" + sb);
    }
}

