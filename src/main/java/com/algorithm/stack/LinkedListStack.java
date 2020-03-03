package com.algorithm.stack;

import com.leetcode.source.ListNode;

/**
 * 基于链表实现的栈
 */
public class LinkedListStack {

    private ListNode top = null;

    public void push(int data) {
        ListNode newNode = new ListNode(data);
        // 判断是否栈空
        if (top == null) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    public int pop() {
        if (top == null) return 0;// 这里我用0表示栈中没有数据
        int data = top.data;
        top = top.next;
        return data;
    }

    public void printAll() {
        ListNode temp = null;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println();
    }

}
