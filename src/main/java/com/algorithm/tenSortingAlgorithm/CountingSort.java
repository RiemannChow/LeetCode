package com.algorithm.tenSortingAlgorithm;

import java.util.Arrays;

public class CountingSort {
    private static int[] countingSort(int[] arr) {
        //1、求取最大值和最小值，计算中间数组的长度：中间数组是用来记录原始数据中每个值出现的频率
        int min = arr[0], max = arr[0];
        for (int i : arr) {
            if (i > max) {
                max = i;
            }
            if (i < min) {
                min = i;
            }
        }

        //2、有了最大值和最小值能够确定中间数组的长度
        //例如存储 5-0+1=6
        int[] countArray = new int[max - min + 1];

        //3、循环遍历旧数组计数排序: 就是统计原始数组值出现的频率到中间数组B中
        for (int i : arr) {
            countArray[i - min] += 1; //数的位置上+1
        }

        //4、统计数组做变形，后边的元素等于前面的元素之和
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i - 1];
        }

        //5、倒序遍历原始数组，从统计数组中找到正确的位置，输出到结果数组
        int[] resultArray = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            //给resultArray的当前位置赋值
            resultArray[countArray[arr[i] - min] - 1] = arr[i];
            //给countArray的位置的值--
            countArray[arr[i] - min]--;
        }
        return resultArray;
    }

    public static void main(String[] args) {
        int[] arr = {1,28,3,21,11,7,6,18};
        int[] sortedArr = countingSort(arr);
        System.out.println(Arrays.toString(sortedArr));
    }
}
