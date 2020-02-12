package com.nowcoder.tencent;

import java.util.Scanner;

public class ConstructingPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            String reverseStr = new StringBuilder(str).reverse().toString();
            int[][] dp = new int[str.length() + 1][str.length() + 1];
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    dp[i][j] = str.charAt(i - 1) == reverseStr.charAt(j - 1) ? dp[i - 1][j - 1] + 1 :
                            Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            System.out.println(str.length() - dp[str.length()][reverseStr.length()]);
        }
        sc.close();
    }
}
