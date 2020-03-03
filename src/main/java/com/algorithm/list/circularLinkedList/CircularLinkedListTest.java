package com.algorithm.list.circularLinkedList;

import org.junit.Test;

/**
 * @author riemann
 * @date 2019/07/14 1:24
 */
public class CircularLinkedListTest {

    @Test
    public void test() {
        CircularLinkedList<String> list = new ConcurrentCircularLinkedList<String>();
        for (int i = 1; i < 101; i++) {
            list.add("" + i);
        }
        int count = 1;
        while (list.size() > 1) {
            list.next();
            count++;
            if (count % 3 == 0) {
                System.out.println(list.remove() + " break!");
                count++;
            }
        }
        System.out.println(list.toString());
    }

}
