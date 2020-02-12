package com.nowcoder.huawei;

import java.util.Scanner;

public class HighestScore {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                char c = sc.next().charAt(0);
                int a = sc.nextInt();
                int b = sc.nextInt();
                if ('Q' == c) {
                    System.out.println(max(nums, a - 1, b - 1));
                }
                if ('U' == c) {
                    nums[a - 1] = b;
                }
            }
        }
        sc.close();
    }

    private static int max(int[] nums, int a, int b) {
        int left, right;
        if (a <= b) {
            left = a;
            right = b;
        } else {
            left = b;
            right = a;
        }
        int max = nums[left];
        for (int i = left; i <= right; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }
        return max;
    }
}
