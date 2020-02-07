package com.nowcoder.huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 */
public class BinaryTransform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            transform(sc.next());
        }
    }

    private static void transform(String input) {
        String str = input.substring(2, input.length());
        int res = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'A' && str.charAt(i) <= 'F') {
                res += res * 15 + str.charAt(i) - 'A' + 10;
            } else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'f') {
                res += res * 15 + str.charAt(i) - 'a' + 10;
            } else {
                res += res * 15 + str.charAt(i) - '0';
            }
        }
        System.out.println(res);
    }
}
