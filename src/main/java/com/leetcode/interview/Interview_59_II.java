package com.leetcode.interview;

import java.util.LinkedList;

/**
 * Interview_59_II-队列的最大值
 *
 * 请定义一个队列并实现函数 max_value 得到队列ø里的最大值，要求函数max_value、push_back 和 pop_front 的时间复杂度都是O(1)。
 *
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 *
 * 示例2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 */
public class Interview_59_II {

    private LinkedList<Integer> data;
    private LinkedList<Integer> maxQueue;

    public Interview_59_II() {
        data = new LinkedList<>();
        maxQueue = new LinkedList<>();
        maxQueue.addLast(Integer.MIN_VALUE);
    }

    public int max_value() {
        if (data.isEmpty()) {
            return -1;
        }
        return maxQueue.peekFirst();
    }

    public void push_back(int value) {
        data.addLast(value);
        while (!maxQueue.isEmpty() && maxQueue.peekLast() < value) {
            maxQueue.removeLast();
        }
        maxQueue.addLast(value);
    }

    public int pop_front() {
        if (data.isEmpty()) {
            return -1;
        }
        int front = data.removeFirst();
        if (maxQueue.peekFirst() == front) {
            maxQueue.removeFirst();
        }
        return front;
    }
}
