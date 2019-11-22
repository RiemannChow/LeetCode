package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class MergeSort {
    private static void mergeSort(int[] arr, int low, int high) {
        if (low < high) { //当子序列中只有一个元素时结束递归
            int mid = (low + high) / 2; //划分子序列
            mergeSort(arr, low, mid); //对左侧子序列进行递归排序
            mergeSort(arr, mid + 1, high); //对右侧子序列进行递归排序
            merge(arr, low, mid, high); //合并
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[arr.length]; //辅助数组
        int k = 0, i = low, j = mid + 1; //i左边序列和j右边序列起始索引,k是存放指针
        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }
        //如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        //同上
        while (j <= high) {
            temp[k++] = arr[j++];
        }
        //复制回原数组
        for (int t = 0; t < k; t++) {
            arr[low + t] = temp[t];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
