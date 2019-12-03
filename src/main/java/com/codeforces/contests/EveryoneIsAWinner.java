package com.codeforces.contests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EveryoneIsAWinner {
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");
    static int[] temp = new int[1024];
    static int c = 0;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int wheel = nextInt();
        for (int i = 0; i < wheel; i++) {
            int n = nextInt();
            c = 0;
            int count = 0;

            StringBuilder sb = new StringBuilder();
            sb.append("0");
            count++;

            int idx = 1;
            while (true) {
                int other = n / idx;
                if (other > idx) {
                    temp[c++] = other;
                    sb.append(" ").append(idx);
                    count++;
                    idx++;
                } else if (other == idx) {
                    sb.append(" ").append(idx);
                    count++;
                    break;
                } else {
                    break;
                }
            }
            c--;
            while (c >= 0) {
                sb.append(" ").append(temp[c--]);
                count++;
            }
            out.println(count);
            out.println(sb.toString());
        }
        out.flush();
    }

    private static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(br.readLine());
        }
        return st.nextToken();
    }
}
