package com.leetcode.code;

import java.util.Arrays;

/**
 * LeetCode322-零钱兑换
 *
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 *
 * 示例1：
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 *
 * 示例2：
 * 输入: coins = [2], amount = 3
 * 输出: -1
 */
public class LeetCode322 {
    // 方法1：DFS深度优先搜索
    private static int ans = Integer.MAX_VALUE;
    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, coins.length - 1, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private static void dfs(int[] coins, int index, int amount, int count) {
        if (index < 0) return;
        for (int i = amount / coins[index]; i >= 0 ; i--) {
            int numAmount = amount - i * coins[index];
            int numCount = count + i;
            if (numAmount == 0) {
                ans = Math.min(ans, numCount);
                break;
            }
            if (numCount + 1 >= ans) break;
            dfs(coins,index - 1, numAmount, numCount);
        }
    }

    // 方法2：动态规划
    public int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
