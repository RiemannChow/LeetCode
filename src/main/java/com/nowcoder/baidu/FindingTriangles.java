package com.nowcoder.baidu;

import java.util.Scanner;

/**
 * 三维空间中有N个点，每个点可能是三种颜色的其中之一，三种颜色分别是红绿蓝，分别用'R', 'G', 'B'表示。
 * 现在要找出三个点，并组成一个三角形，使得这个三角形的面积最大。
 * 但是三角形必须满足：三个点的颜色要么全部相同，要么全部不同。
 *
 * 输入描述:
 * 首先输入一个正整数N三维坐标系内的点的个数.(N <= 50)
 * 接下来N行，每一行输入 c x y z，c为'R', 'G', 'B' 的其中一个。x，y，z是该点的坐标。(坐标均是0到999之间的整数)
 *
 * 输出描述:
 * 输出一个数表示最大的三角形面积，保留5位小数。
 *
 * 输入例子1:
 * 5
 * R 0 0 0
 * R 0 4 0
 * R 0 0 3
 * G 92 14 7
 * G 12 16 8
 *
 * 输出例子1:
 * 6.00000
 */
public class FindingTriangles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            int[] x = new int[num];
            int[] y = new int[num];
            int[] z = new int[num];
            char[] colorArr = new char[num];
            for (int i = 0; i < num; i++) {
                colorArr[i] = sc.next().charAt(0);
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
                z[i] = sc.nextInt();
            }
            double maxArea = 0;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (i == j) continue;
                    for (int k = 0; k < num; k++) {
                        if (k == i || k == j) continue;
                        if (colorArr[k] == colorArr[i] && colorArr[k] == colorArr[j]) {
                            // 3个相同颜色的点
                            double area = getArea(x[i], y[i], z[i], x[j], y[j], z[j], x[k], y[k], z[k]);
                            if (area > maxArea) {
                                maxArea = area;
                            }
                        } else if (colorArr[k] != colorArr[i] && colorArr[k] != colorArr[j] && colorArr[i] != colorArr[j]) {
                            // 3个不同颜色的点
                            double area = getArea(x[i], y[i], z[i], x[j], y[j], z[j], x[k], y[k], z[k]);
                            if(area > maxArea){
                                maxArea = area;
                            }
                        }
                    }
                }
            }
            System.out.println(String.format("%.5f", maxArea));
        }
        sc.close();
    }

    // 海伦公式
    //（p=(a+b+c)/2）
    // S=sqrt[p(p-a)(p-b)(p-c)]
    private static double getArea(int x1, int y1, int z1, int x2, int y2, int z2, int x3, int y3, int z3) {
        double a = Math.sqrt((x1-x2) * (x1-x2) + (y1-y2) * (y1-y2) + (z1-z2) * (z1-z2));
        double b = Math.sqrt((x2-x3) * (x2-x3) + (y2-y3) * (y2-y3) + (z2-z3) * (z2-z3));
        double c = Math.sqrt((x1-x3) * (x1-x3) + (y1-y3) * (y1-y3) + (z1-z3) * (z1-z3));
        double p = (a + b + c) / 2;
        double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
        return area;
    }
}
