package com.leetcode.code;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode6-Z字形变换
 *
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 4 时，排列如下：
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 */
public class LeetCode6 {
    public static String convert(String s, int numRows) {
        int length = s.length();
        if(length == 0 || length <= numRows || numRows == 1) {
            return s;
        }
        int gap = numRows - 2;
        String ans = "";
        int k1 = numRows -1 + gap + 1;
        for (int i = 0; i < numRows; i++) {
            int location = 0;
            if (i==0 || i == numRows - 1) {
                ans = ans + s.charAt(i);
                location = i + k1;
                while (location < length) {
                    ans = ans + s.charAt(location);
                    location = location + k1;
                }
            } else {
                ans = ans + s.charAt(i);
                int k2 = k1 - i * 2;
                location  = location + k2 + i;
                while (location < length) {
                    ans = ans + s.charAt(location);
                    k2 = k1 - k2;
                    location  = k2 + location;
                }
            }
        }
        return ans;
    }

    public static String convert2(String s, int numRows) {
        if (numRows < 2) {
            return s;
        }
        List<StringBuilder> rows = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            rows.add(new StringBuilder());
        }
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            rows.get(i).append(c);
            if (i == 0 || i == numRows - 1) {
                flag = - flag;
            }
            i += flag;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 4)); //472103 纳秒
        System.out.println(convert2("LEETCODEISHIRING", 4)); // 666246 纳秒
    }
}
