package com.leetcode.code;

/**
 * /**
 *  * LeetCode155-最小栈（链式栈实现）
 *  *
 *  * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *  *
 *  * push(x) -- 将元素 x 推入栈中。
 *  * pop() -- 删除栈顶的元素。
 *  * top() -- 获取栈顶元素。
 *  * getMin() -- 检索栈中的最小元素。
 *  *
 *  * 示例：
 *  * MinStack minStack = new MinStack();
 *  * minStack.push(-2);
 *  * minStack.push(0);
 *  * minStack.push(-3);
 *  * minStack.getMin();   --> 返回 -3.
 *  * minStack.pop();
 *  * minStack.top();      --> 返回 0.
 *  * minStack.getMin();   --> 返回 -2.
 *  */
public class LeetCode155_2 {

     private Node head;

     public void push(int x) {
         if (head == null) {
             head = new Node(x, x);
         } else {
             head = new Node(x, Math.min(x, head.min), head);
         }
     }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min) {
            this(val, min, null);
        }

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

}
