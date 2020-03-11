package com.algorithm.recursive;

/**
 * 二分查找
 */
public class BinarySearch {
    /**
     * @param array      有序数组,但不限于数组
     * @param start       开始查找的数组下标
     * @param end         结束查找的数组下标
     * @param searchValue 要搜索的值
     * @return
     */
    public static int search(int[] array, int start, int end, int searchValue) {
        if (array != null && array.length > 0) {
            int middle = (start + end) / 2;
            int middleValue = array[middle];
            if (searchValue == middleValue) {
                return middle;
            } else if (searchValue < middleValue) {
                // 查询值小于中值,在中值前面再次搜索,缩小范围
                return search(array, start, middle - 1, searchValue);
            } else {
                // 查询值大于中值,在中值后面再次搜索,缩小范围
                return search(array, middle + 1, end, searchValue);
            }
        } else {
            return -1;
        }
    }
}
