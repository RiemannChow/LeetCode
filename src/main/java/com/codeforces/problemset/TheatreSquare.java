package com.codeforces.problemset;

import java.util.Scanner;

public class TheatreSquare {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();
        long a = sc.nextLong();
        System.out.println(theatreSquare(n, m, a));
    }

    private static long theatreSquare(long n, long m, long a) {
        long length = n % a == 0 ? n / a : n / a + 1;
        long width = m % a == 0 ? m / a : m / a + 1;
        long count = length * width;
        return count;
    }
}
