package com.codeforces.contests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class SweetProblem {
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int wheel = nextInt();
        for (int i = 0; i < wheel; i++) {
            int r = nextInt();
            int g = nextInt();
            int b = nextInt();
            int min = min(r, min(g, b));
            int max = max(r, max(g, b));
            int sum = r + g + b;
            int mid = sum - max - min;
            int diff = max - mid;
            if (diff > min) {
                pw.println(min(max, sum - max));
            } else {
                min -= diff;
                mid += diff;
                int plus = min / 2;
                pw.println(mid + plus);
            }
        }
        pw.close();
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
