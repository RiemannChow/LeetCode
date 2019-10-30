package com.leetcode.code;

/**
 * LeetCode7-整数反转
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 * 输入: 123
 * 输出: 321
 *
 *  示例 2:
 *  输入: -123
 * 输出: -321
 *
 * 示例 3:
 * 输入: 120
 * 输出: 21
 *
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 */
public class LeetCode7 {
    public static int reverse(int x) {
        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE) {
            return 0;
        }
        int abs = Math.abs(x), num = 0;
        String str = new StringBuilder(String.valueOf(abs)).reverse().toString();
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
        return num = x > 0 ? num : -num;
    }

    public static int reverse2(int x) {
        long rs = 0;
        while(x != 0){
            rs = rs * 10 + x % 10;
            x /= 10;
        }
        return (rs < Integer.MIN_VALUE || rs > Integer.MAX_VALUE) ? 0 : (int) rs;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-12300));
        System.out.println(reverse2(-12300));
    }
}
