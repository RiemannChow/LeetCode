package com.leetcode.code;

import java.util.Stack;

/**
 * LeetCode682-棒球比赛
 *
 * 你现在是棒球比赛记录员。
 * 给定一个字符串列表，每个字符串可以是以下四种类型之一：
 * 1.整数（一轮的得分）：直接表示您在本轮中获得的积分数。
 * 2. "+"（一轮的得分）：表示本轮获得的得分是前两轮有效 回合得分的总和。
 * 3. "D"（一轮的得分）：表示本轮获得的得分是前一轮有效 回合得分的两倍。
 * 4. "C"（一个操作，这不是一个回合的分数）：表示您获得的最后一个有效 回合的分数是无效的，应该被移除。
 *
 * 每一轮的操作都是永久性的，可能会对前一轮和后一轮产生影响。
 * 你需要返回你在所有回合中得分的总和。
 *
 * 示例1：
 * 输入: ["5","-2","4","C","D","9","+","+"]
 * 输出: 27
 * 解释:
 * 第1轮：你可以得到5分。总和是：5。
 * 第2轮：你可以得到-2分。总数是：3。
 * 第3轮：你可以得到4分。总和是：7。
 * 操作1：第3轮的数据无效。总数是：3。
 * 第4轮：你可以得到-4分（第三轮的数据已被删除）。总和是：-1。
 * 第5轮：你可以得到9分。总数是：8。
 * 第6轮：你可以得到-4 + 9 = 5分。总数是13。
 * 第7轮：你可以得到9 + 5 = 14分。总数是27。
 */
public class LeetCode682 {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0;
        for (String str : ops) {
            if (str.equals("+")) {
                int pop1 = stack.pop();
                int pop2 = pop1 + stack.peek();
                sum += pop2;
                stack.push(pop1);
                stack.push(pop2);
            } else if (str.equals("D")) {
                sum += 2 * stack.peek();
                stack.push(2 * stack.peek());
            } else if (str.equals("C")) {
                sum -= stack.pop();
            } else {
                sum += Integer.parseInt(str.substring(0));
                stack.push(Integer.parseInt(str.substring(0)));
            }
        }
        return sum;
    }
}
