package com.nowcoder.huawei;

import java.util.Scanner;

/**
 * 有这样一道智力题：“某商店规定：三个空汽水瓶可以换一瓶汽水。小张手上有十个空汽水瓶，
 * 她最多可以换多少瓶汽水喝？”答案是5瓶，方法如下：先用9个空瓶子换3瓶汽水，喝掉3瓶满的，
 * 喝完以后4个空瓶子，用3个再换一瓶，喝掉这瓶满的，这时候剩2个空瓶子。然后你让老板先借给你一瓶汽水，
 * 喝掉这瓶满的，喝完以后用3个空瓶子换一瓶满的还给老板。如果小张手上有n个空汽水瓶，
 * 最多可以换多少瓶汽水喝？
 */
public class SodaBottle {
    private static int blank;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            blank = sc.nextInt();
            int drink = 0;
            int remain;
            if (blank == 0) break;
            if (blank > 0 || blank <= 100) {
                if (blank == 1) {
                    drink = 0;
                } else if (blank == 2) {
                    drink = 1;
                }
                while (blank > 2) {
                    drink += blank / 3;
                    remain = blank % 3;
                    blank = blank / 3 + remain;
                    if (blank == 2) {
                        drink++;
                    }
                }
            }
            System.out.println(drink + "");
        }
    }
}