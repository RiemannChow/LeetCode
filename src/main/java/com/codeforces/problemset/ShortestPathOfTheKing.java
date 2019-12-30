package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShortestPathOfTheKing {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int sX = s.charAt(0) - 'a';
        int sY = s.charAt(1) - '1';

        s = br.readLine();
        int tX = s.charAt(0) - 'a';
        int tY = s.charAt(1) - '1';

        int diffX = sX - tX;
        int diffY = sY - tY;

        StringBuilder sb = new StringBuilder();

        char[] xs = new char[Math.abs(diffX)];
        char ss = 0;

        if (diffX > 0) {
            ss = 'L';
        } else if (diffX < 0) {
            ss = 'R';
        }

        for (int i = 0; i < xs.length; i++) {
            xs[i] = ss;
        }

        char[] ys = new char[Math.abs(diffY)];

        if (diffY > 0) {
            ss = 'D';
        } else if (diffY < 0) {
            ss = 'U';
        }

        for (int i = 0; i < ys.length; i++) {
            ys[i] = ss;
        }

        int n = Math.max(ys.length, xs.length);
        sb.append(n);
        sb.append("\n");

        for (int i = 0; i < n; i++) {
            if (xs.length > i) {
                sb.append(xs[i]);
            }
            if (ys.length > i) {
                sb.append(ys[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
