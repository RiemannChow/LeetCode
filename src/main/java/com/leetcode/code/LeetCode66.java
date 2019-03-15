package com.leetcode.code;

import java.util.Scanner;

/**
 * 输入: [1,2,3]
 * 输出: [1,2,4]
 * 解释: 输入数组表示数字 123。
 */

public class LeetCode66 {
    public static int[] plusOne(int[] digits) {
        boolean flag = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                flag = false;
                break;
            }
        }
        if (flag) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        } else {
            for (int i = digits.length - 1; i >= 0; i--) {
                if (digits[i] != 9) {
                    digits[i] += 1;
                    break;
                } else {
                    digits[i] = 0;
                }
            }
        }
        return digits;

    }

    public static void main(String[] args) {

        int[] digits = new int[] {6,8,9};
        int[] re = plusOne(digits);
        System.out.print("[");
        for (int i = 0; i < re.length; i++) {
            if(i == re.length - 1){
                System.out.print(re[i]);
            }else{
                System.out.print(re[i]+",");
            }

        }
        System.out.print("]");

    }
}