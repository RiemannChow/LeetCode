package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CommentatorProblem {

    private static BufferedReader in;
    private static PrintWriter out;
    private static StringTokenizer st;

    static int[] x = new int[3];
    static int[] y = new int[3];
    static int[] r = new int[3];
    static double[] d = new double[3];

    public static void main(String[] args) throws IOException {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(System.out);
        double dx = 0, dy = 0;
        for (int i = 0; i < 3; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
            r[i] = nextInt();
            dx += x[i];
            dy += y[i];
        }
        dx /= 3;
        dy /= 3;
        double step = 1.0;
        while (step > 1e-6) {
            if (deviation(dx, dy) > deviation(dx + step, dy))
                dx += step;
            else if (deviation(dx, dy) > deviation(dx - step, dy))
                dx -= step;
            else if (deviation(dx, dy) > deviation(dx, dy + step))
                dy += step;
            else if (deviation(dx, dy) > deviation(dx, dy - step))
                dy -= step;
            else
                step *= 0.7;
        }
        if (deviation(dx, dy) < 1e-5) {
            out.printf("%.5f %.5f\n", dx, dy);
        }
        out.close();
    }

    private static double deviation(double dx, double dy) {
        for (int i = 0; i < 3; i++) {
            // Distances from point to centers / r
            d[i] = Math.sqrt(sqr(x[i] - dx) + sqr(y[i] - dy)) / r[i];
        }
        // Value for minimization
        double s = sqr(d[0] - d[1]) + sqr(d[1] - d[2]) + sqr(d[2] - d[0]);
        return s;
    }

    public static String next() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            st = new StringTokenizer(in.readLine());
        }
        return st.nextToken();
    }

    private static int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    private static Long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    private static Double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    public static double sqr(double x) {
        return x * x;
    }
}
