package com.leetcode.code;

import java.util.Arrays;

/**
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 */

public class LeetCode16 {
    public static int threeSumClosest(int[] nums, int target) {
        if(nums.length < 3){
           return 0;
        }
        Arrays.sort(nums);
        int result = 0;
        int cha = Integer.MAX_VALUE;
        int tempCha = 0;
        for (int one = 0; one < nums.length - 2; one++) {
            int left = one + 1;
            int right = nums.length - 1;
            while(left < right){
                int tempSum = nums[one] + nums[left] + nums[right];
                tempCha = Math.abs(tempSum - target);
                if(tempCha < cha){
                    cha = tempCha;
                    result = tempSum;//这里的潜在的最后的正确结果没注意保存
                }
                if(tempSum == target){
                    return target;
                }
                if(tempSum < target){
                    left++;
                }else{
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,2,1,-4};
        System.out.println(threeSumClosest(nums,1));
    }
}
