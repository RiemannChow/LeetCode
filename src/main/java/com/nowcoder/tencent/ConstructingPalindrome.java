package com.nowcoder.tencent;

import java.util.Scanner;

/**
 * 求从一个字符串中最少删除几个字符能得到一个最长的回文串
 *
 * 输入例子1:
 * abcda
 * google
 *
 * 输出例子1:
 * 2
 * 2
 */
public class ConstructingPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            int len = str.length();
            String reverseStr = new StringBuilder(str).reverse().toString();
            // 动态规划求最长公共子序列长度
            int[][] dp = new int[len + 1][len + 1];
            // 注意起始位置
            for (int i = 1; i < dp.length; i++) {
                for (int j = 1; j < dp[0].length; j++) {
                    dp[i][j] = str.charAt(i - 1) == reverseStr.charAt(j - 1) ? dp[i - 1][j - 1] + 1 :
                            Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            // dp[len][len] 即为原串与逆序串的最长公共子序列长度
            System.out.println(len - dp[len][len]);
        }
        sc.close();
    }
}
