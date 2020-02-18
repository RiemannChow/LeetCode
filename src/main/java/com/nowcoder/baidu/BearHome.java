package com.nowcoder.baidu;

import java.util.Scanner;

/**
 * 一个数轴上共有N个点，第一个点的坐标是度度熊现在位置，第N-1个点是度度熊的家。现在他需要依次的从0号坐标走到N-1号坐标。
 * 但是除了0号坐标和N-1号坐标，他可以在其余的N-2个坐标中选出一个点，并直接将这个点忽略掉，问度度熊回家至少走多少距离？
 *
 * 输入描述:
 * 输入一个正整数N, N <= 50。
 * 接下来N个整数表示坐标，正数表示X轴的正方向，负数表示X轴的负方向。绝对值小于等于100
 *
 * 输出描述:
 * 输出一个整数表示度度熊最少需要走的距离。
 *
 * 输入例子1:
 * 4
 * 1 4 -1 3
 *
 * 输出例子1:
 * 4
 */
public class BearHome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int count = sc.nextInt();
            int[] nums = new int[count];
            for (int i = 0; i < count; i++) {
                nums[i] = sc.nextInt();
            }
            int sum = 0;
            int maxReduce = Integer.MIN_VALUE;
            int currReduce;
            for (int i = 1; i < count - 1; i++) {
                sum += Math.abs(nums[i] - nums[i - 1]);
                currReduce = Math.abs(nums[i] - nums[i - 1]) + Math.abs(nums[i + 1] - nums[i])
                        - Math.abs(nums[i + 1] - nums[i - 1]);
                maxReduce = Math.max(currReduce, maxReduce);
            }
            sum += Math.abs(nums[count - 1] - nums[count - 2]);
            sum -= maxReduce;
            System.out.println(sum);
        }
        sc.close();
    }
}
