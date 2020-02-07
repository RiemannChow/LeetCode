package com.nowcoder.huawei;

import java.util.Scanner;

/**
 * 明明想在学校中请一些同学一起做一项问卷调查，为了实验的客观性，
 * 他先用计算机生成了N个1到1000之间的随机整数（N≤1000），
 * 对于其中重复的数字，只保留一个，把其余相同的数去掉，不同的数对应着不同的学生的学号。
 * 然后再把这些数从小到大排序，按照排好的顺序去找同学做调查。
 * 请你协助明明完成“去重”与“排序”的工作(同一个测试用例里可能会有多组数据，希望大家能正确处理)。
 */
public class RandomNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n <= 0 || n > 1000) {
                return;
            }
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int[] dArr = deduplication(arr);
            for (int i = 0; i < 1000; i++) {
                if (dArr[i] == 1) {
                    System.out.println(i);
                }
            }
        }
    }

    private static int[] deduplication(int[] arr) {
        int[] d = new int[1000];
        for (int i = 0; i < 1000; i++) {
            d[i] = 0;
        }
        for (int i = 0; i < arr.length; i++) {
            d[arr[i]] = 1;
        }
        return d;
    }
}
