package com.codeforces.problemset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShortestPathOfTheKing2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();
        String[] arr = new String[100];

        char sX = s.charAt(0);
        char sY = s.charAt(1);
        char tX = t.charAt(0);
        char tY = t.charAt(1);
        int n = 0;

        while (true) {
            if (sX < tX && sY > tY) {
                arr[n] = "RD";
                n++;
                sX += 1;
                sY -= 1;
            } else if (sX < tX && sY < tY) {
                arr[n] = "RU";
                n++;
                sX += 1;
                sY += 1;
            } else if (sX > tX && sY > tY) {
                arr[n] = "LD";
                n++;
                sX -= 1;
                sY -= 1;
            } else if (sX > tX && sY < tY) {
                arr[n] = "LU";
                n++;
                sX -= 1;
                sY += 1;
            } else if (sX == tX && sY < tY) {
                arr[n] = "U";
                n++;
                sY += 1;
            } else if (sX == tX && sY > tY) {
                arr[n] = "D";
                n++;
                sY -= 1;
            } else if (sX > tX && sY == tY) {
                arr[n] = "L";
                n++;
                sX -= 1;
            } else if (sX < tX && sY == tY) {
                arr[n] = "R";
                n++;
                sX += 1;
            } else if (sX == tX && sY == tY) {
                break;
            }
        }
        System.out.println(n);
        for (int i = 0; i < n; i++) {
            System.out.println(arr[i]);
        }
    }
}
