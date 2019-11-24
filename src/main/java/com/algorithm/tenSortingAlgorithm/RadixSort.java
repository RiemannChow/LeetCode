package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class RadixSort {
    private static void radixSort(int[] arr) {
        // 获取数组中最大值
        int max = findMax(arr);

        // 需要遍历的次数由数组最大值的位数来决定
        for (int i = 1; max / i > 0; i = i * 10) {
            int[][] buckets = new int[arr.length][10];
            // 获取每一位数字(个、十、百、千位...分配到桶子里)
            for (int j = 0; j < arr.length; j++) {
                int num = (arr[j] / i) % 10;
                // 将其放入桶子里
                buckets[j][num] = arr[j];
            }

            // 回收桶子里的元素
            int k = 0;

            // 有10个桶子
            for (int j = 0; j < 10; j++) {
                // 对每个桶子里的元素进行回收
                for (int t = 0; t < arr.length; t++) {
                    // 如果桶子里面有元素就回收(数据初始化会为0)
                    if (buckets[t][j] != 0) {
                        arr[k++] = buckets[t][j];
                    }
                }
            }
        }
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {1,28,29,3,21,11,7,6,18};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
