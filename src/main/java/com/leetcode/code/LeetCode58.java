package com.leetcode.code;

/**
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。
 *
 * 如果不存在最后一个单词，请返回 0 。
 *
 * 说明：一个单词是指由字母组成，但不包含任何空格的字符串。
 * 输入: "Hello World"
 * 输出: 5
 */
public class LeetCode58 {
    public int lengthOfLastWord(String s){
        String[] list = s.split(" ");
        if (list.length == 0){
            return 0;
        }
        return list[list.length - 1].length();//返回最后一个字符串的长度
    }
}
