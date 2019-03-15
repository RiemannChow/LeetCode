package com.leetcode.code;

/**
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class LeetCode26 {
    public static int removeDuplicates(int[] nums){
        if(nums.length == 0){
            return 0;
        }
        if(nums.length == 1){
            return 1;
        }
        int first = 0;
        int last = 1;
        int count = 1;
        while (last < nums.length){
            if(nums[last] == nums[first]){
                last++;
            }else{
                count++;
                first++;
                nums[first] = nums[last];
                last++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,1,2,3,3};
        System.out.println(removeDuplicates(nums));
    }
}
