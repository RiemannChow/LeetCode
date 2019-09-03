package com.test;

import java.math.BigDecimal;

/**
 * @author riemann
 * @date 2019/08/21 20:47
 */
public class TwoLongAddTest {

    public static void main(String[] args) {
        Long a = 2147483648L;
        Long b = 2147483648L;
        BigDecimal add = new BigDecimal(a).add(new BigDecimal(b));
        System.out.println(add.longValue());
    }

}
