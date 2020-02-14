package com.nowcoder.tencent;

import java.util.Scanner;

/**
 * 小Q最近遇到了一个难题：把一个字符串的大写字母放到字符串的后面，
 * 各个字符的相对位置不变，且不能申请额外的空间。
 *
 * 输入例子1:
 * AkleBiCeilD
 *
 * 输出例子1:
 * kleieilABCD
 */
public class CharacterShift {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = false;
        while (sc.hasNext()) {
            String str = sc.next();
            char[] chars = str.toCharArray();
            int len = str.length();
            for (int i = len - 1; i >= 0 ; i--) {
                check = false;
                if (Character.isLowerCase(chars[i])) {
                    for(int j = i - 1; j >= 0; j--) {
                        if (Character.isUpperCase(chars[j])) {
                            char temp = chars[j];
                            for (int k = j; k < i; k++) {
                                chars[k] = chars[k + 1];
                            }
                            chars[i] = temp;
                            check = true;
                        }
                        if (check) break;
                    }
                }
            }
            System.out.println(String.valueOf(chars));
        }
        sc.close();
    }
}
