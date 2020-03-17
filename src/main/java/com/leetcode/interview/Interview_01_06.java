package com.leetcode.interview;

/**
 * Interview_01_06-字符串压缩
 *
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
 * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
 * 你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 示例1:
 * 输入："aabcccccaaa"
 * 输出："a2b1c5a3"
 *
 * 示例2:
 * 输入："abbccd"
 * 输出："abbccd"
 * 解释："abbccd"压缩后为"a1b2c2d1"，比原字符串长度更长。
 */
public class Interview_01_06 {
    public String compressString(String s) {
        if (s == null || s.length() <= 2) return s;
        StringBuilder sb = new StringBuilder().append(s.charAt(0));
        int count = 1;
        char[] chars = s.toCharArray();

        for (int i = 1; i < s.length(); i++) {
            // 如果i与i-1相同，count累加
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                // 否则拼接上i-1的次数，从i开始重新计数
                sb.append(count).append(chars[i]);
                count = 1;
            }
        }
        return sb.append(count).length() < s.length() ? sb.toString() : s;
    }
}
