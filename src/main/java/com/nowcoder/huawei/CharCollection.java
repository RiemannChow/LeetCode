package com.nowcoder.huawei;

import java.util.Scanner;

/**
 * 输入一个字符串，求出该字符串包含的字符集合。
 */
public class CharCollection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!sb.toString().contains(c + "")) {
                    sb.append(c);
                }
            }
            System.out.println(sb.toString());
        }
        sc.close();
    }
}
