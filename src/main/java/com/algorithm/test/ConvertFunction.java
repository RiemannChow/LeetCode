package com.algorithm.test;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;

/**
 * @author riemann
 * @date 2019/09/05 23:05
 */
public class ConvertFunction {

    public static void main(String[] args) {
        String str = "www.jd.cn";
        System.out.println("----------------");
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i));
        }
        System.out.println();
        System.out.println("----------------");

        System.out.println("变换前: " + str);
        System.out.println("变换后: " + reverse1(str));
        System.out.println("变换后: " + reverse2(str));
        System.out.println("变换后: " + reverse3(str));
        System.out.println("变换后: " + reverse4(str));
        System.out.println("变换后: " + reverse5(str));
    }

    // StringBuilder
    public static String reverse1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    // toCharArray
    public static String reverse2(String str) {
        char[] chars = str.toCharArray();
        String reverse = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            reverse += chars[i];
        }
        return reverse;
    }

    // charAt
    public static String reverse3(String str) {
        String reverse = "";
        int length = str.length();
        for (int i = 0; i < length; i++) {
            reverse = str.charAt(i) + reverse;
        }
        return reverse;
    }

    // substring()
    public static String reverse4(String str) {
        if (StringUtils.isEmpty(str))
            return str;
        return str.charAt(str.length() - 1) + reverse4(str.substring(0, str.length() - 1));
    }

    // 交换字符反转
    public static String reverse5(String str) {
        char[] chars = str.toCharArray();
        int length = chars.length - 1;
        int half = (int) Math.floor(chars.length / 2);
        char c;
        for (int i = length; i >= half ; i--) {
            c = chars[i];
            chars[i] = chars[length - i];
            chars[length - i] = c;
        }
        return String.valueOf(chars);
    }

}
