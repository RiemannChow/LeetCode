package com.algorithm.stack;

import java.io.IOException;

/**
 * 栈在括号匹配中的应用
 */
public class BracketMatchingStack {
    public static void main(String[] args) throws IOException {
        byte[] buf = new byte[128];
        int length = System.in.read(buf);
        ByteStack stack = new ByteStack(buf, length);

        for (int i = 0; i < length; i++) {
            if (buf[i] == '(' || buf[i] == '[' || buf[i] == '{') {
                stack.push(buf[i]);
            } else if (buf[i] == ')') {
                if (stack.getTop() == '(')
                    stack.pop();
                else {
                    System.out.println("1 unmatch!");
                    System.exit(1);
                }
            } else if (buf[i] == ']') {
                if (stack.getTop() == '[')
                    stack.pop();
                else {
                    System.out.println("2 unmatch!");
                    System.exit(1);
                }
            }
            else if (buf[i] == '}') {
                if (stack.getTop() == '{')
                    stack.pop();
                else {
                    System.out.println("3 unmatch!");
                    System.exit(1);
                }
            }
        }

        if (!stack.isEmpty()) {
            System.out.println("4 unmatch!");
        } else {
            System.out.println("matched~");
        }
    }
}
