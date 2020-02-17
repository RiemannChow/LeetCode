package com.nowcoder.alibaba;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GrabRedEnvelope {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> list = null;
        double money = 0;
        Random random = new Random();
        while (sc.hasNext()) {
            double totalMoney = sc.nextDouble();
            int count = sc.nextInt();
            list = new ArrayList<>(count);
            while (count > 1) {
                double max = totalMoney * 0.3;
                double r = random.nextDouble();
                money = r * max;
                if (money < 1) {
                    money = 1;
                } else {
                    money = Math.floor(money * 100) / 100;
                }
                list.add(money);
                count--;
                totalMoney -= money;
            }
            list.add(Math.floor(totalMoney * 100) / 100);
            System.out.println(list);
        }
        sc.close();
    }
}
