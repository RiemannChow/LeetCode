package com.leetcode.interview;

/**
 * Interview_10_01-合并排序的数组
 *
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 *
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 *
 * 示例：
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 *
 * 输出: [1,2,2,3,5,6]
 */
public class Interview_10_01 {
    public void merge(int[] A, int m, int[] B, int n) {
        int a = m - 1, b = n - 1, cur = m + n - 1;
        while (a >= 0 && b >= 0) A[cur--] = A[a] > B[b] ? A[a--] : B[b--];
        while (a >= 0) A[cur--] = A[a--];
        while (b >= 0) A[cur--] = B[b--];
    }
}
