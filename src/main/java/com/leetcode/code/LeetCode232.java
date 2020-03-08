package com.leetcode.code;

import java.util.Stack;

/**
 * LeetCode232-用栈实现队列
 *
 * 使用栈实现队列的下列操作：
 *
 * push(x) -- 将一个元素放入队列的尾部。
 * pop() -- 从队列首部移除元素。
 * peek() -- 返回队列首部的元素。
 * empty() -- 返回队列是否为空。
 *
 * 示例：
 * MyQueue queue = new MyQueue();
 *
 * queue.push(1);
 * queue.push(2);
 * queue.peek();  // 返回 1
 * queue.pop();   // 返回 1
 * queue.empty(); // 返回 false
 *
 */
public class LeetCode232 {

    Stack<Integer> stackPush;
    Stack<Integer> stackPop;

    /** Initialize your data structure here. */
    public LeetCode232() {
        stackPush = new Stack<>();
        stackPop = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackPush.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
        return stackPop.pop();
    }

    /** Get the front element. */
    public int peek() {
        if (stackPush.isEmpty() && stackPop.isEmpty()) {
            throw new RuntimeException("Queue is empty.");
        } else {
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.push(stackPush.pop());
                }
            }
        }
        return stackPop.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackPop.isEmpty() && stackPush.isEmpty();
    }
}
