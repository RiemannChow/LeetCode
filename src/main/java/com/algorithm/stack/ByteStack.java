package com.algorithm.stack;

/**
 * 基于字符数组实现的顺序栈
 */
public class ByteStack {

    private byte[] bytes;    // 字符数组
    private int count;       // 栈中元素的个数
    private int n;           // 栈的大小

    // 初始化数组，申请一个大小为n的数组空间
    public ByteStack(byte[] bytes, int n) {
        this.bytes = new byte[n];
        this.count = 0;
        this.n = n;
    }

    // 入栈操作
    public boolean push(byte item) {
        if (count == n) return false;
        bytes[count] = item;
        count++;
        return true;
    }

    // 出栈操作
    public byte pop() {
        if (count == 0) return 0;
        byte temp = bytes[count - 1];
        --count;
        return temp;
    }

    // 获取栈顶元素的值，不出栈。
    public byte getTop() {
        if (count == 0) return 0;
        return bytes[count - 1];
    }

    // 判断栈是否为空
    public boolean isEmpty() {
        return count == 0;
    }
}
