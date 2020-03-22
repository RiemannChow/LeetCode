package com.leetcode.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * LeetCode18-四数之和
 *
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 *   [-1,  0, 0, 1],
 *   [-2, -1, 1, 2],
 *   [-2,  0, 0, 2]
 * ]
 */

public class LeetCode18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        HashSet<List<Integer>> set = new HashSet<>();
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length == 4){
            if(nums[0] + nums[1] + nums[2] + nums[3] == target){
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[0]);
                temp.add(nums[1]);
                temp.add(nums[2]);
                temp.add(nums[3]);
                result.add(temp);
                return result;
            }
            return result;
        }
        Arrays.sort(nums);
        for(int begin = 0; begin < nums.length - 3; begin++){
            for (int end = nums.length - 1; end - begin > 2; end--){
                int left = begin + 1;
                int right = end - 1;
                while(left < right){
                    int tempSum = nums[begin] + nums[left] + nums[right] + nums[end];
                    if(tempSum == target){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[begin]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        temp.add(nums[end]);
                        set.add(temp);
                        left++;
                    }else if(tempSum < target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }
        for (List<Integer> t: set){
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 0, -1, 0, -2, 2};
        System.out.println(fourSum(nums,0));
    }
}
