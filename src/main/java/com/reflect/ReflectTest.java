package com.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws Exception {

        System.out.println("A(无参构造函数)--加载类、反射类的构造函数、利用构造函数new一个Animal实例instance--");

        //1、加载类,指定类的完全限定名：包名+类名
        Class c1 = Class.forName("com.reflect.Animal");
        System.out.println(c1); //打印c1，发现值和字节码中的类的名称一样

        //2、解刨(反射)类c1的公开构造函数，且参数为null
        Constructor ctor1 = c1.getConstructor();

        //3、构造函数的用途，就是创建类的对象（实例）的
        //除了私有构造函数外（单列模式，禁止通过构造函数创建类的实例，保证一个类只有一个实例）
        //ctor1.newInstance()默认生成一个Object对象,我们需要转化成我们要的Animal类对象
        Animal a1 = (Animal) ctor1.newInstance();

        //4、证明一下a1确实是Animal的实例，我们通过访问类中的变量来证明
        System.out.println(a1.name);

        System.out.println("A(有参构造函数)--加载类、反射类的构造函数、利用构造函数new一个Animal实例instance--");

        //2.b、 解刨(反射)类c1的公开构造函数，参数为string和int
        Constructor ctor2 = c1.getConstructor(String.class, int.class);
        Animal a2 = (Animal) ctor2.newInstance("cat", 20);

        System.out.println("B--获得本类中的所有的字段----------------------------");

        //5、获得类中的所有的字段	包括public、private和protected，不包括父类中申明的字段
        Field[] fields = c1.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("C--获得本类中的所有公有的字段，并获得指定对象的字段值-----");

        //6、获得类中的所有的公有字段
        fields = c1.getFields();
        for (Field field : fields) {
            System.out.println(field + ", 字段值 = " + field.get(a1));
            //注意：私有变量值，无法通过field.get(a1)进行获取值
            //通过反射类中的字段name，修改name的值（注意，原值在类中name="Dog"）
            //如果，字段名称等于"name"，且字段类型为String，我们就修改字段的值，也就是类中变量name的值
            if (field.getName() == "name" && field.getType().equals(String.class)) {
                String name_new = (String) field.get(a1); //记得转换一下类型
                name_new = "哈士奇"; //重新给name赋值
                field.set(a1, name_new); //设置当前实例a1的name值，使修改后的值生效
            }
        }

        System.out.println("利用反射出的字段，修改字段值，修改后的name = "+a1.name);
        System.out.println("D--获取本类中的所有的方法--------------------");

        //7、获取本类中所有的方法 包括public、private和protected，不包括父类中申明的方法
        Method[] methods = c1.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(m);//我们在类Animal中只定义了一个public方法，sayName
        }

        System.out.println("E--获取本类中的所有的公有方法，包括父类中和实现接口中的所有public方法-----------");

        //8、获取类中所有公有方法，包括父类中的和实现接口中的所有public 方法
        methods = c1.getMethods();
        for (Method m : methods) {
            System.out.println(m);//我们在类Animal中只定义了一个public方法，sayName
        }

        System.out.println("F--根据方法名称和参数类型获取指定方法，并唤起方法：指定所属对象a1，并给对应参数赋值-----------");

        //9、唤起Method方法(执行) getMethod:第一个参数是方法名，后面跟方法参数的类
        Method sayName = c1.getMethod("sayName", String.class);
        System.out.println(sayName.invoke(a1, "riemann"));

    }
}
