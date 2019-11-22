package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class InsertionSort {
    private static void insertionSort(int[] arr) {
        int preIndex, current;
        for (int i = 1; i < arr.length; i++) {
            preIndex = i - 1;
            current = arr[i];
            while (preIndex >= 0 && arr[preIndex] > current) {
                arr[preIndex + 1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex + 1] = current;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
