package com.nowcoder.tencent;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小Q今天在上厕所时想到了这个问题：有n个数，两两组成二元组，相差最小的有多少对呢？相差最大呢？
 *
 * 输入例子1:
 * 6
 * 45 12 45 32 5 6
 *
 * 输出例子1:
 * 1 2
 */
public class InterestingNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int[] arr = new int[num];
            for (int i = 0; i < num; i++) {
                arr[i] = sc.nextInt();
            }
            Arrays.sort(arr);
            int[] a = find(arr);
            System.out.println(a[0] + " " + a[1]);
        }
    }

    private static int[] find(int[] arr) {
        int[] result = new int[2];// result[0]是min,result[1]是max
        if (arr[0] == arr[arr.length - 1]) {// 特殊情况，最大值与最小值相等
            result[0] = result[1] = arr.length * (arr.length - 1);
            return result;
        } else {// 求最大值
            int a = 0, b = 0;
            while (arr[a] == arr[0]) {
                a++;
            }
            while (arr[arr.length - 1 - b] == arr[arr.length - 1]) {
                b++;
            }
            result[1] = a * b;
        }
        // 求最小值
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i]) {
                min = 0;
                result[0] = 0;
                break;
            }
            if (arr[i + 1] - arr[i] < min) {
                min = arr[i + 1] - arr[i];
                result[0] = 1;
            } else if (arr[i + 1] - arr[i] == min) {
                result[0]++;
            }
        }
        if (min == 0) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i + 1] == arr[i]) {
                    int temp = 1;
                    while (i + temp < arr.length && arr[i + temp] == arr[i]) {
                        temp++;
                    }
                    result[0] = result[0] + ((temp - 1) * temp) / 2;
                    i = i + temp - 1;
                }
            }
        } else {
            return result;
        }
        return result;
    }
}
