package com.test;

/**
 * final域为基本类型
 *
* @author riemann
* @date 2019/07/17 23:24
*/
public class FinalDemo {

    private int a;                        //普通变量
    private final int b;                  //final变量
    private static FinalDemo finalDemo;

    public FinalDemo() {      //构造函数
        a = 1;                //写普通域
        b = 2;                //写final域
    }

    public static void writer() {    //写线程A执行
        finalDemo = new FinalDemo();
    }

    public static void reader() {        //读线程B执行
        FinalDemo object = finalDemo;    //读对象引用
        int a = object.a;                //读普通域
        int b = object.b;                //读final域
    }

}
