package com.leetcode.code;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 */
public class LeetCode387 {
    public static void main(String[] args) {
        String s = "loveleetcode";
        System.out.println(firstUniqChar(s));
    }
    public static int firstUniqChar(String s){
        int index = 1;
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++){
            int cha = s.charAt(i) - 97; //根据ASC码 字符转数组下标
            if (array[cha] == 0){//说明第一次出现
                array[cha] = index;//把index值赋值给他
                index++;//然后index务必+1，这样字符串中以后的字符就算第一次出现，也是index值比第一个第一次出现的index的值大的。
            }else{
                array[cha] = -1;//如果已经出现了，那么就数组赋值为-1
            }
        }
        int resultIndex = Integer.MAX_VALUE;
        char result = '#';
        for (int i = 0; i < array.length; i++){//遍历数组，找到index最小的那个字符
            if (array[i] > 0){
                if (array[i] < resultIndex){//每次都找较小的index的，因为index越小，在字符串的位置越靠前，也就是越靠近第一次出现的字符
                    resultIndex = array[i];
                    result = (char) (i + 97);
                }
            }
        }
        if (result == '#'){
            return -1;//没找到，返回-1
        }
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == result){
                return i;//因为要返回字符在字符串的下标，所以去字符串中去寻找字符的下标，然后返回。
            }
        }
        return -1;
    }
}
