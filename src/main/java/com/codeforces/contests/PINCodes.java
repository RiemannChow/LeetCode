package com.codeforces.contests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PINCodes {
    static BufferedReader br;
    static StringTokenizer st = new StringTokenizer("");

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int wheel = nextInt();
        for (int i = 0; i < wheel; i++) {
            int len = nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = nextInt();
            }
            int ans = 0;
            for (int x = 0; x < len; x++) {
                boolean oFlag = false;
                while (true) {
                    boolean iFlag = false;
                    for (int y = 0; y < len; y++) {
                        if (y == x) continue;
                        if (arr[x] == arr[y]) {
                            iFlag = true;
                            oFlag = true;
                            arr[x]++;
                            if (arr[x] % 10 == 0) arr[x] -= 10;
                            break;
                        }
                    }
                    if (!iFlag) break;
                }
                if (oFlag) ans++;
            }
            out.println(ans);
            for (int x = 0; x < len; x++) {
                if (arr[x] < 1000) out.print("0");
                if (arr[x] < 100) out.print("0");
                if (arr[x] < 10) out.print("0");
                out.println(arr[x]);
            }
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
