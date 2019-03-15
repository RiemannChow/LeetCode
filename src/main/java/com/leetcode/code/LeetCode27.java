package com.leetcode.code;

/**
 * 给定 nums = [0,1,2,2,3,0,4,2], val = 2,
 *
 * 函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
 *
 * 注意这五个元素可为任意顺序。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class LeetCode27 {
    public static void main(String[] args) {
        int[] nums = {2,0,1,3,4,1,3};
        System.out.println(removeElement(nums,3));
    }
    public static int removeElement(int[] nums, int val){
        int begin = 0;
        int end = nums.length - 1;
        int count = 0;

        while (begin <= end){
            while(begin < nums.length && nums[begin] != val){
                begin++;
            }
            while (end >= 0 && nums[end] == val){
                count++;
                end--;
            }
            if(begin >= end || begin >= nums.length || end < 0){
                break;
            }
            int temp = nums[begin];
            nums[begin] = nums[end];
            nums[end] = temp;
        }
        return nums.length - count;
    }
}
