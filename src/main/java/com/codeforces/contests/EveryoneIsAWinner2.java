package com.codeforces.contests;

import java.io.*;
import java.util.LinkedList;

public class EveryoneIsAWinner2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int wheel = Integer.parseInt(br.readLine());

        while (wheel-- > 0) {
            int n = Integer.parseInt(br.readLine());

            LinkedList<Integer> stack = new LinkedList<Integer>();
            int threshold = (int) Math.sqrt(n);
            int divide = 1;
            while (n / divide > threshold) {
                stack.push(n / divide);
                divide++;
            }

            out.println(stack.size() + threshold + 1);
            for (int i = 0; i <= threshold; i++) {
                out.print(i + " ");
            }
            while (!stack.isEmpty()) {
                out.print(stack.pop() + " ");
            }
            out.println();
        }
        out.flush();
    }
}
