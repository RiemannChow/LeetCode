package com.leetcode.code;

/**
 * LeetCode1160-拼写单词
 *
 * 给你一份『词汇表』（字符串数组） words 和一张『字母表』（字符串） chars。
 * 假如你可以用 chars 中的『字母』（字符）拼写出 words 中的某个『单词』（字符串），那么我们就认为你掌握了这个单词。
 * 注意：每次拼写时，chars 中的每个字母都只能用一次。
 * 返回词汇表 words 中你掌握的所有单词的 长度之和。
 *
 * 示例：
 * 输入：words = ["hello","world","leetcode"], chars = "welldonehoneyr"
 * 输出：10
 * 解释：
 * 可以形成字符串 "hello" 和 "world"，所以答案是 5 + 5 = 10。
 */
public class LeetCode1160 {
    public static int countCharacters(String[] words, String chars) {
        int[] arrChars = new int[26];
        for (int i = 0; i < chars.length(); i++) {
            arrChars[chars.charAt(i) - 'a']++;
        }
        // 单词长度
        int count = 0;
        for (String str : words) {
            if (canSpellWords(str, arrChars)) count += str.length();
        }
        return count;
    }

    private static boolean canSpellWords(String str, int[] arrChars) {
        int[] temp = new int[26];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // 临时表 和 字母表值相等，代表字母不存在
            if (temp[c - 'a'] == arrChars[c - 'a']) return false;
            temp[c - 'a']++;
        }
        return true;
    }
}
