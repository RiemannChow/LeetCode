package com.leetcode.code;

import java.util.Arrays;

/**
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出: [1,2,2,3,5,6]
 * 16-22行就是从数组nums1和数组nums2的后面开始遍历，谁大就把谁存到nums1的后面（这里注意看示例，nums1的后面是一堆0，代表空闲的空间，m和nums1的数组长度是不一样的~）
 * 23-25行 就是如果nums1前面有数字没遍历完，那么就把nums1的数字继续保留到nums1中，其实这6行代码可以不要，因为nums1的数字就是有序的，不需要再次复制了，nums1的前面的数字已经存在于nums1中了；
 * 26-28行 就是如果nums2前面还有数字没遍历完，那么就把nums2中数字复制到nums1中，这里是必须要要用到，因为nums2中数字可不在nums1中。
 */

public class LeetCode88 {
    public static int[] merge(int[] nums1, int m, int[] nums2, int n){
        int i = m - 1;
        int j = n - 1;
        int k = m   + n -1;
        while(i >= 0 && j >= 0){
            if(nums1[i] > nums2[j]){
                nums1[k--] = nums1[i--];
            }else{
                nums1[k--] = nums2[j--];
            }
        }
        while(i >= 0){
            nums1[k--] = nums1[i--];
        }
        while(j >= 0){
            nums1[k--] = nums2[j--];
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        System.out.println(Arrays.toString(merge(nums1,3,nums2,3)));
    }
}
