package com.nowcoder.baidu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 度度熊想去商场买一顶帽子，商场里有N顶帽子，有些帽子的价格可能相同。
 * 度度熊想买一顶价格第三便宜的帽子，问第三便宜的帽子价格是多少？
 *
 * 输入描述:
 * 首先输入一个正整数N（N <= 50），接下来输入N个数表示每顶帽子的价格（价格均是正整数，且小于等于1000）
 *
 * 输出描述:
 * 如果存在第三便宜的帽子，请输出这个价格是多少，否则输出-1
 *
 * 输入例子1:
 * 10
 * 10 10 10 10 20 20 30 30 40 40
 *
 * 输出例子1:
 * 30
 */
public class BuyHat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            int[] nums = new int[count];
            for (int i = 0; i < count; i++) {
                nums[i] = sc.nextInt();
            }
            Arrays.sort(nums);
            int level = 1;
            for (int i = 1; i < count; i++) {
                if (nums[i - 1] < nums[i]) {
                    level++;
                }
                if (level == 3) {
                    System.out.println(nums[i]);
                    break;
                }
            }
            if (level < 3) {
                System.out.println("-1");
            }
        }
        sc.close();
    }
}
