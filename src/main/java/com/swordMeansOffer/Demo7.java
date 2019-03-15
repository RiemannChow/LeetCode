package com.swordMeansOffer;

/**
 * 大家都知道斐波那契数列f(n) = f(n-1) + f(n-2)，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Demo7 {
    public static void main(String[] args) {
        int n = 6;
        System.out.println(Fibonacci(n));
    }
    public static int Fibonacci(int n){
        int a = 0;
        int b = 1;
        if (n == 0){
            return 0;
        }
        if (n == 1){
            return 1;
        }
        int i = 2;
        int sum = 0;
        while(i <= n){
            sum = a + b;
            a = b;
            b = sum;
            i++;
        }

        return sum;
    }
}
