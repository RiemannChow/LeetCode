package com.test;

import java.util.Objects;

public class TestEquals {

    private static long TEN_THOUSAND = 10000;

    public static void main(String[] args) {
        long times = 1000 * TEN_THOUSAND;
        long start = System.currentTimeMillis();
        testEquals(times);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    public static void testEquals(long times) {
        String a = null;
        String b = "b";
        Object Object = null;
        for(int i=0; i<times; i++){
//          if (a.equals(b)) {} //23ms
//          if (Objects.equals(a, b)) {} //44ms
//          if (a == b) {} //16ms
//          if(Objects.equals(a, b) //17ms
            if (Object.equals(b)) {} //空指针异常
        }

    }

}
