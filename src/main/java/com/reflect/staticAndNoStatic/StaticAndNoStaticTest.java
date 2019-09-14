package com.reflect.staticAndNoStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author riemann
 * @date 2019/09/14 15:16
 */
public class StaticAndNoStaticTest {

    public static void main(String[] args) throws Exception {

        // 反射调用非静态方法，需要获取类对象
        // 获取字节码对象
        Class<StaticAndNoStaticTest> clazz = (Class<StaticAndNoStaticTest>) Class.forName("com.reflect.staticAndNoStatic.StaticAndNoStaticTest");
        // 获取一个对象
        Constructor<StaticAndNoStaticTest> constructor = clazz.getConstructor();
        StaticAndNoStaticTest instance = constructor.newInstance();
        String[] s = new String[]{"riemann", "chow"};
        // 获取Method对象
        Method noStaticMethod = clazz.getMethod("NoStaticMethod", String[].class);
        // 调用invoke方法来调用
        noStaticMethod.invoke(instance, (Object) s);
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

}
