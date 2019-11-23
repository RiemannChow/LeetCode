package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class ShellSort {
    private static void shellSort(int[] arr) {
        int gap = arr.length / 2;
        int temp;
        while (gap >= 1) {
            for (int i = gap; i < arr.length; i++) {
                temp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
            gap = gap / 2;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
