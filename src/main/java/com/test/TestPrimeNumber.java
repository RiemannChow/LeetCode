package com.test;

import java.util.Scanner;

/**
 * @author riemann
 * @date 2019/04/12 20:35
 */
public class TestPrimeNumber {
    public static void main(String[] args) {
        System.out.println("请输入了一个大于2的整数:");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();//707829217
        int x = 3;//所有数里出现3
        primeCount(input);//707829217
        long start = System.currentTimeMillis();
        System.out.println("3总共出现了: " + occurrencesCount(input,x) + "次");
        long end = System.currentTimeMillis();
        System.out.println("执行共耗时: " + (end - start) + "ms");
    }

    private static void primeCount(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(num + "=");
        int minPrimeNumber = 2;// 定义最小的质数
        while (minPrimeNumber < num) {
            if (num % minPrimeNumber == 0) {
                num = num / minPrimeNumber;
                sb.append(minPrimeNumber + "*");
            } else {
                minPrimeNumber++;
            }
        }
        sb.append(minPrimeNumber);
        System.out.println(sb.toString());
    }

    private static int occurrencesCount(int n, int x) {
        if (n < 0 || x < 1 || x > 9) {
            return 0;
        }
        int low, cur, high = n, temp, i = 1, total = 0;
        while (high != 0) {
            high = (int) (n / Math.pow(10, i));             //获取第i位的高位
            temp = (int) (n % Math.pow(10, i));
            cur = (int) (temp / Math.pow(10, i - 1));       //获取第i位
            low = (int) (temp % Math.pow(10, i - 1));       //获取第i位的低位
            if (cur == x) {
                total += high * Math.pow(10, i - 1) + low + 1;
            } else if (cur < x) {
                total += high*(int) Math.pow(10, i-1);
            } else {//大于x
                total += (high + 1) * (int)Math.pow(10, i-1);
            }
            i++;
        }
        return total;
    }
}
