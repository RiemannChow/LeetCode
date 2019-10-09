package com.test;

/**
 * 考察a+=b和a=a+b的区别，主要是精度的区别
 *
 * @author riemann
 * @date 2019/10/09 17:47
 */
public class AccuracyTest {
    public static void main(String[] args) {
        short a = 1;
        a = (short) (a + 2);
        System.out.println(a);
    }

    /*public static void main(String[] args) {
        Long a = 1L;
        a = a + 2;
        System.out.println(a);
    }*/
}
