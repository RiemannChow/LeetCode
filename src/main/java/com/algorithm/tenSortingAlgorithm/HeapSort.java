package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class HeapSort {
    private static void heapSort(int[] arr) {
        // 构造初始堆（大顶堆）,从第一个非叶子节点开始调整,左右孩子节点中较大的交换到父节点中
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapAdjust(arr, i, arr.length);
        }
        // 调整堆结构，交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            swap(arr, 0, j);// 将堆顶元素与末尾元素进行交换
            heapAdjust(arr, 0, j);// 重新对堆进行调整
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    // 调整大顶堆
    private static void heapAdjust(int[] arr, int i, int len) {
        int temp = arr[i], index = 2 * i + 1;
        while (index < len) {
            if (index + 1 < len && arr[index] < arr[index + 1]) {// 如果左子结点小于右子结点，index指向右子结点
                index += 1;
            }
            if (arr[index] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点
                arr[i] = arr[index];
                i = index;
                index = 2 * i + 1;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
