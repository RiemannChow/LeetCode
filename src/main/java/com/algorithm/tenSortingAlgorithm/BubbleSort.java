package com.algorithm.tenSortingAlgorithm;

import java.util.Random;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = constructDataArray(1000);
        System.out.println("===============排序前===============");
        printArrayData(arr);
        long start = System.currentTimeMillis();
        bubbleSort6(arr, 1000);
        System.out.println("===============排序后===============");
        printArrayData(arr);
        System.out.println("排序后耗时：" + (System.currentTimeMillis() - start) + "ms");
    }

    private static void printArrayData(int[] arr) {
        for (int a : arr) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    private static int[] constructDataArray(int len) {
        int[] arr = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            arr[i] = random.nextInt(len);
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr, int len) {
        for (int i = 0; i < len; i++) { //确定排序趟数
            for (int j = i + 1; j < len; j++) { //确定比较次数
                if (arr[i] > arr[j]) { //交换
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    // 优化第一版
    public static void bubbleSort2(int[] arr, int len) {
        for (int i = 0; i < len; i++) {
            boolean isSorted = true;
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    // 优化第二版
    public static void bubbleSort3(int[] arr, int len) {
        int border = len - 1, lastIndex = 0;
        for (int i = 0; i < len; i++) {
            boolean isSorted = true;
            for (int j = 0; j < border; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    lastIndex = j;
                    isSorted = false;
                }
            }
            border = lastIndex;
            if (isSorted) {
                break;
            }
        }
    }

    // 优化第三版 (鸡尾酒算法)
    public static void bubbleSort4(int[] arr, int len) {
        int temp = 0;
        boolean isSorted = true;
        for (int i = 0; i < len / 2 - 1; i++) {
            // 奇数轮比较
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }

            // 偶数轮比较
            for (int j = len - i - 1; j > i; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    // 优化第四版 (鸡尾酒算法优化)
    public static void bubbleSort5(int[] arr, int len) {
        int temp = 0;
        boolean isSorted = true;
        int lastLeftIndex = 0, lastRightIndex = 0;
        //左边界
        int leftBorder = 0;
        //右边界
        int rightBorder = arr.length - 1;

        for (int i = 0; i < len / 2 - 1; i++) {
            // 奇数轮比较
            for (int j = leftBorder; j < rightBorder; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    lastRightIndex = j;
                    isSorted = false;
                }
            }
            rightBorder = lastRightIndex;
            if (isSorted) {
                break;
            }

            // 偶数轮比较
            for (int j = rightBorder; j > leftBorder; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                    lastLeftIndex = j;
                    isSorted = false;
                }
            }
            leftBorder = lastLeftIndex;
            if (isSorted) {
                break;
            }
        }
    }

    // 优化第五版 (引入标志位，只循环一次)
    public static void bubbleSort6(int[] arr, int len) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int i = 0; i < len - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    flag = true;
                }
            }
            len--;
        }
    }

}
