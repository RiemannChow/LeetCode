package com.leetcode.code;

import java.util.Stack;

/**
 * LeetCode224-基本计算器
 *
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 *
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格。
 *
 * 示例1：
 * 输入: " 2-1 + 2 "
 * 输出: 3
 *
 * 示例2：
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 */
public class LeetCode224 {

    Stack<Integer> stack = new Stack<Integer>();
    int operand = 0;
    int result = 0; // For the on-going result
    int sign = 1;  // 1 means positive, -1 means negative

    public int calculate(String s) {
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                operand = 10 * operand + (int) (c - '0');
            } else if (c == '+') {
                result += sign * operand;
                // Save the recently encountered '+' sign
                sign = 1;
                // Reset operand
                operand = 0;
            } else if (c == '-') {
                result += sign * operand;
                sign = -1;
                operand = 0;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                // Reset operand and result, as if new evaluation begins for the new sub-expression
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * operand;
                // ')' marks end of expression within a set of parenthesis
                // Its result is multiplied with sign on top of stack
                // as stack.pop() is the sign before the parenthesis
                result *= stack.pop();
                // Then add to the next operand on the top.
                // as stack.pop() is the result calculated before this parenthesis
                // (operand on stack) + (sign on stack * (result from parenthesis))
                result += stack.pop();
                // Reset the operand
                operand = 0;
            }
        }
        return result + (sign * operand);
    }
}
