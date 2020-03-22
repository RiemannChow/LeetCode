package com.algorithm.dp;

public class KMP {
    private int[][] dp;
    private String pat;

    public KMP(String pat) {
        this.pat = pat;
        int m = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[m][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影⼦状态 shadow 初始为 0
        int shadow = 0;
        // 构建状态转移图
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < 256; j++) {
                dp[i][j] = dp[shadow][j];
            }
            dp[i][pat.charAt(i)] = i + 1;
            // 更新影⼦状态
            shadow = dp[shadow][pat.charAt(i)];
        }
    }

    public int search(String txt) {
        int m = pat.length();
        int n = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < n; i++) {
            // 计算 pat 的下⼀个状态
            j = dp[j][txt.charAt(j)];
            // 到达终⽌态，返回结果
            if (j == m) return i - m + 1;
        }
        // 没到达终⽌态，匹配失败
        return -1;
    }

    public static void main(String[] args) {
        KMP kmp = new KMP("ABABC");
        System.out.println(kmp.search("SABABCA"));
    }
}
