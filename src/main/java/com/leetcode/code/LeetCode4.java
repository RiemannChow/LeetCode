package com.leetcode.code;

import java.util.Arrays;

/**
 * LeetCode4-寻找两个有序数组的中位数
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 * 示例1：
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 *
 * 示例：
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class LeetCode4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length, sum = 0;
        double mid = 0;
        if (len1 == 0 && len2 == 0) {
            return 0.0;
        }
        int[] nums3 = Arrays.copyOf(nums1, len1 + len2); //建立nums3数组，并将nums1添加进去
        int len3 = nums3.length;
        System.arraycopy(nums2, 0, nums3, len1, len2); //将nums2数组添加到已经含有nums2数组的nums3数组中去
        Arrays.sort(nums3); //对nums3数组进行排序

        if (len3 % 2 == 0) {
            mid = (nums3[(len3 / 2) - 1] + nums3[len3 / 2]) / 2d; //这里要除以2d,这样才能得到一个中位数
        } else {
            mid = nums3[len3 / 2];
        }
        return mid;
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        if (m == 0 && n == 0) {
            return 0.0;
        }
        int left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k。
        return (getKth(nums1, 0, m - 1, nums2, 0, n - 1, left) + getKth(nums1, 0, m - 1, nums2, 0, n - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1, len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) {
            return getKth(nums2, start2, end2, nums1, start1, end1, k);
        }
        if (len1 == 0) {
            return nums2[start2 + k -1];
        }
        if (k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        } else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(LeetCode4.findMedianSortedArrays(nums1, nums2));
        System.out.println(LeetCode4.findMedianSortedArrays2(nums1, nums2));
    }
}
