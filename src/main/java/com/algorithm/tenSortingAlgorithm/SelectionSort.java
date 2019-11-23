package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class SelectionSort {
    private static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 最小数的索引
            int minIndex = i;
            // 找到最小数，并记录最小数的索引
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
