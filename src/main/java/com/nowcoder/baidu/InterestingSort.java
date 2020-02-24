package com.nowcoder.baidu;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * 度度熊有一个N个数的数组，他想将数组从小到大 排好序，但是萌萌的度度熊只会下面这个操作：
 * 任取数组中的一个数然后将它放置在数组的最后一个位置。
 * 问最少操作多少次可以使得数组从小到大有序？
 *
 * 输入描述:
 * 首先输入一个正整数N，接下来的一行输入N个整数。(N <= 50, 每个数的绝对值小于等于1000)
 *
 * 输出描述:
 * 输出一个整数表示最少的操作次数。
 *
 * 输入例子1:
 * 4
 * 19 7 8 25
 *
 * 输出例子1:
 * 2
 */
public class InterestingSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            int i = 0;
            for (i = 0; i < n; i++) {
                map.put(nums[i], i);
            }
            Arrays.sort(nums);
            int count = 0;
            for (int j = 0; j < n - 1; j++) {
                if (map.get(nums[j + 1]) < map.get(nums[j])) {
                    map.put(nums[j + 1], i++);
                    count++;
                }
            }
            System.out.println(count);
        }
        sc.close();
    }
}
