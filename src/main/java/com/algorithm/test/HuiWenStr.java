package com.algorithm.test;

import java.util.Scanner;

/**
 * @author riemann
 * @date 2019/09/29 12:55
 */
public class HuiWenStr {

    public static final String LOWER_CASE_LETTER = "^[a-z]+$";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串A: ");
        String A = scanner.nextLine();
        System.out.println("请输入字符串B: ");
        String B = scanner.nextLine();

        if (!verification(A , B)) {
            System.out.println("输入的字符串不合法！");
            return;
        }

        int count = 0;
        for (int i = 0; i < A.length(); i++) {
            StringBuilder sb = new StringBuilder(A);
            sb.insert(i, B);
            if (isHuiWen(sb.toString())) {
                count++;
            }
        }
        System.out.println("回文个数: " + count + "个");
    }

    private static boolean verification(String a, String b) {
        if (a.matches(LOWER_CASE_LETTER) && b.matches(LOWER_CASE_LETTER) && a.length() < 100 && b.length() < 100) {
            return true;
        }
        return false;
    }

    private static boolean isHuiWen(String str) {
        int left = 0;
        int right = str.length() - 1;
        while (left < right) {
            if (str.charAt((left)) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
