package com.leetcode.code;

/**
 * LeetCode5-最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 */
public class LeetCode5 {
    // 方法一：暴力破解
    public static String longestPalindrome(String s) {
        int len = s.length(), max = 0;
        String palindrome = "";
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                String content = s.substring(i, j);
                if (isPalindromic(content) && content.length() > max) {
                    palindrome = s.substring(i, j);
                    max = Math.max(max, palindrome.length());
                }
            }
        }
        return palindrome;
    }

    private static boolean isPalindromic(String content) {
        int len = content.length();
        for (int i = 0; i < len / 2; i++) {
            if (content.charAt(i) != content.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    // 方法二：Manacher's Algorithm 马拉车算法
    public static String longestPalindrome2(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        // 第一步：预处理，将原字符串转换为新字符串
        String t = "$";
        for (int i = 0; i < len; i++) {
            t += "#" + s.charAt(i);
        }
        // 尾部再加上字符@，变为奇数长度字符串
        t += "#@";
        // 第二步：计算数组p、起始索引、最长回文半径
        int n = t.length();
        // p数组
        int[] p = new int[n];
        int id = 0, pRightBoundary = 0; // 回文串右边界
        // 最长回文子串的长度
        int maxLength = -1;
        // 最长回文子串的中心位置索引
        int centerIndex = 0;
        for (int j = 1; j < n - 1; j++) {
            p[j] = pRightBoundary > j ? Math.min(p[2 * id - j], pRightBoundary - j) : 1;
            // 向左右两边延伸，扩展右边界
            while (t.charAt(j + p[j]) == t.charAt(j - p[j])) {
                p[j]++;
            }
            // 如果回文子串的右边界超过了pRightBoundary，则需要更新pRightBoundary和id的值
            if (pRightBoundary < p[j] + j) {
                pRightBoundary = p[j] + j;
                id = j;
            }
            // 如果回文子串的长度大于maxLength，则更新maxLength和index的值
            if (maxLength < p[j] - 1) {
                maxLength = p[j] - 1;
                centerIndex = j;
            }
        }
        // 第三步：截取字符串，输出结果
        int start = (centerIndex - maxLength) / 2;
        return s.substring(start, start + maxLength);
    }

    // 方法三: 二分法
    public static String longestPalindrome3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 保存起始位置，测试了用数组似乎能比全局变量稍快一点
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
        // 把回文看成中间的部分全是同一字符，左右部分相对称
        // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        // 查找中间部分
        int high = low;
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        // 定位中间部分的最后一个字符
        int ans = high;
        // 从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        // 记录最大长度
        if (high - low > range[1] - range[0]) {
            range[0] = low;
            range[1] = high;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));// 721410 纳秒
        System.out.println(longestPalindrome2("babad"));// 704732 纳秒
        System.out.println(longestPalindrome3("babad"));// 376314 纳秒
    }
}
