package com.reflect;

public class ClassloaderAndForNameTest {
    public static void main(String[] args) {
        String wholeNameLine = "com.reflect.Line";
        String wholeNamePoint = "com.reflect.Point";
        System.out.println("下面是测试Classloader的效果");
        testClassloader(wholeNameLine, wholeNamePoint);
        System.out.println("----------------------------------");
        System.out.println("下面是测试Class.forName的效果");
        testForName(wholeNameLine, wholeNamePoint);
    }

    /**
     * classloader
     * @param wholeNameLine
     * @param wholeNamePoint
     */
    private static void testClassloader(String wholeNameLine, String wholeNamePoint) {
        Class<?> line;
        Class<?> point;
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        try {
            line = loader.loadClass(wholeNameLine);
            point = loader.loadClass(wholeNamePoint);
            System.out.println("line " + line.getName());
            System.out.println("point " + point.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Class.forName
     * @param wholeNameLine
     * @param wholeNamePoint
     */
    private static void testForName(String wholeNameLine, String wholeNamePoint) {
        try {
            Class<?> line = Class.forName(wholeNameLine);
            Class<?> point = Class.forName(wholeNamePoint);
            System.out.println("line " + line.getName());
            System.out.println("point " + point.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
