package com.reflect.staticAndNoStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author riemann
 * @date 2019/09/14 15:16
 */
public class StaticAndNoStaticTest {

    public static void main(String[] args) throws Exception {

        System.out.println("==========反射调用静态方法==========");
        // 反射调用静态方法,不需要获取类对象
        // 获取字节码对象
        Class<StaticAndNoStaticTest> clazzStatic = (Class<StaticAndNoStaticTest>) Class.forName("com.reflect.staticAndNoStatic.StaticAndNoStaticTest");
        String[] s = new String[]{"riemann", "chow"};
        // 获取Method对象
        Method staticMethod = clazzStatic.getMethod("staticMethod", String[].class);
        // 调用invoke方法来调用
        staticMethod.invoke(null, (Object) s);

        System.out.println("==========反射调用非静态方法==========");
        // 反射调用非静态方法，需要获取类对象
        // 获取字节码对象
        Class<StaticAndNoStaticTest> clazzNoStatic = (Class<StaticAndNoStaticTest>) Class.forName("com.reflect.staticAndNoStatic.StaticAndNoStaticTest");
        // 获取一个对象
        Constructor<StaticAndNoStaticTest> constructor = clazzNoStatic.getConstructor();
        StaticAndNoStaticTest instance = constructor.newInstance();
        String[] ss = new String[]{"riemann", "chow"};
        // 获取Method对象
        Method noStaticMethod = clazzNoStatic.getMethod("NoStaticMethod", String[].class);
        // 调用invoke方法来调用
        noStaticMethod.invoke(instance, (Object) ss);

        System.out.println("==========反射调用私有静态方法==========");
        Class<StaticAndNoStaticTest> clazzStaticPrivate = (Class<StaticAndNoStaticTest>) Class.forName("com.reflect.staticAndNoStatic.StaticAndNoStaticTest");
        Constructor<StaticAndNoStaticTest> constructor2 = clazzStaticPrivate.getConstructor();
        StaticAndNoStaticTest instance2 = constructor2.newInstance();
        Method staticPrivateMethod = clazzStaticPrivate.getDeclaredMethod("staticPrivateMethod", new Class[]{String.class});
        staticPrivateMethod.setAccessible(true);
        staticPrivateMethod.invoke(instance2, "test static and private");

        System.out.println("==========反射调用私有非静态方法==========");
        Class<StaticAndNoStaticTest> clazzNoStaticPrivate = (Class<StaticAndNoStaticTest>) Class.forName("com.reflect.staticAndNoStatic.StaticAndNoStaticTest");
        Constructor<StaticAndNoStaticTest> constructor3 = clazzStaticPrivate.getConstructor();
        StaticAndNoStaticTest instance3 = constructor3.newInstance();
        Method noStaticPrivateMethod = clazzNoStaticPrivate.getDeclaredMethod("noStaticPrivateMethod", new Class[]{String.class, Integer.class});
        noStaticPrivateMethod.setAccessible(true);
        noStaticPrivateMethod.invoke(instance3, "test no static and private ", 20190914);
    }

    public static void staticMethod(String[] args) {
        for (String str : args) {
            System.out.println(str);
        }
    }

    public void NoStaticMethod(String[] args) {
        for (String str : args) {
            System.out.println(str);
        }
    }

    private static void staticPrivateMethod(String s) {
        System.out.println("this is a private static method and the parameters is: " + s);
    }

    private void noStaticPrivateMethod(String s, Integer i) {
        System.out.println("this is a private no static method and the parameters is: " + s + i);
    }

}
