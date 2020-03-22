package com.leetcode.code;

import java.util.*;

/**
 * LeetCode155-最小栈（顺序栈实现）
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 * 示例：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 */
public class LeetCode155 {
    private int[] items;
    private int index;
    private int min = Integer.MAX_VALUE;

    public LeetCode155() {
        items = new int[10];
        this.index = 0;
    }

    public void push(int x) {
        // 判断是否扩容
        checkGrowth();
        if (min > x) min = x;
        items[index] = x;
        index++;
    }

    private void checkGrowth() {
        if (index >= items.length) {
            // 使用右移位运算符，每次扩容一半
            items = Arrays.copyOf(items, items.length + (items.length >> 1));
        }
    }

    public int pop() {
        int top = top();
        index--;
        if (top > this.min) return top;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < index; i++) {
            if (min > items[i]) {
                min = items[i];
            }
        }
        this.min = min;
        return top;
    }

    public int top() {
        if (items == null) {
            throw new NullPointerException();
        }
        // 指针前移一位
        return items[index - 1];
    }

    public int getMin() {
        return min;
    }
}
