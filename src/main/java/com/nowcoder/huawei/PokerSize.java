package com.nowcoder.huawei;

import java.util.Scanner;

public class PokerSize {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] left,right;
        String[] line;
        String nextLine, outString;
        while (sc.hasNext()) {
            nextLine = sc.nextLine();
            // 有王炸就王炸最大
            if (nextLine.contains("joker JOKER")) {
                outString = "joker JOKER";
            } else {
                // 拆分 先拆成左右 再拆成单排
                line = nextLine.split("-");
                left = line[0].split(" ");
                right = line[1].split(" ");

                // 炸弹最大
                if (left.length == 4 && right.length != 4) {
                    outString = line[0];
                } else if (left.length != 4 && right.length == 4) {
                    outString = line[1];
                } else if (left.length == right.length) {// 牌数相同的情况下比较最小的牌的大小，compare方法返回牌所对应的值
                    if (count(left[0]) > count(right[0])) {
                        outString = line[0];
                    } else {
                        outString = line[1];
                    }
                } else {
                    outString = "ERROR";
                }
            }
            System.out.println(outString);
        }
    }

    private static int count(String str) {
        return "345678910JQKA2jokerJOKER".indexOf(str);
    }
}
