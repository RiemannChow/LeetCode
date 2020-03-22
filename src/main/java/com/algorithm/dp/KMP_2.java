package com.algorithm.dp;

public class KMP_2 {
    private static int[] next;
    private void getNextValue(String pattern) {
        int pLen = pattern.length();
        next = new int[pLen];
        next[0] = -1;
        int i = -1, j = 0;
        while (j < pLen - 1) {
            // p[i]表示前缀，p[j]表示后缀
            if (i == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                ++i;
                ++j;
                if (pattern.charAt(i) != pattern.charAt(j)) {
                    next[j] = i;
                } else {
                    // 因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
                    next[j] = next[i];
                }
            } else {
                i = next[i];
            }
        }
    }

    private int kmpSearch(String pattern, String text) {
        int i = 0, j = 0;
        int tLen = text.length();
        int pLen = pattern.length();
        while (i < tLen && j < pLen) {
            // 如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++
            if (j == -1 || pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            } else {
                // 如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                // next[j]即为j所对应的next值
                j = next[j];
            }
        }
        if (j == pLen)
            return i - j;
        else
            return -1;
    }

    public static void main(String[] args) {
        KMP_2 kmp = new KMP_2();
        kmp.getNextValue("BBCD");
        System.out.println(kmp.kmpSearch("BBCD", "BBCCABBCDB"));
    }
}
