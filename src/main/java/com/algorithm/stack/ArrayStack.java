package com.algorithm.stack;

/**
 * 基于数组实现的顺序栈
 */
public class ArrayStack {
    private String[] items;  // 数组
    private int count;       // 栈中元素的个数
    private int n;           // 栈的大小

    // 初始化数组，申请一个大小为n的数组空间
    public ArrayStack(String[] items, int n) {
        this.items = new String[n];
        this.count = 0;
        this.n = n;
    }

    // 入栈操作
    public boolean push(String item) {
        if (count == n) return false;
        items[count] = item;
        count++;
        return true;
    }

    // 出栈操作
    public String pop() {
        if (count == 0) return null;
        String temp = items[count - 1];
        --count;
        return temp;
    }

    // 获取栈顶元素
    public String getTop() {
        if (count == 0) return null;
        return items[count - 1];
    }

    // push达到栈的容量时
    public void pushExpansion(String item) {
        if (count == n) {
            n = n * 2;
            String[] newItems = new String[n];
            for (int i = 0; i < items.length; i++) {
                newItems[i] = items[i];
            }
            // 清空原数组
            items = null;
            // 重新赋值
            items = newItems;
        }
        items[count] = item;
        ++count;
    }

}