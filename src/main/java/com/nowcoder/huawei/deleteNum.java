package com.nowcoder.huawei;

import java.util.Scanner;

/**
 * 有一个数组a[N]顺序存放0~N-1，要求每隔两个数删掉一个数，到末尾时循环至开头继续进行，
 * 求最后一个被删掉的数的原始下标位置。以8个数(N=7)为例:｛0，1，2，3，4，5，6，7｝，
 * 0->1->2(删除)->3->4->5(删除)->6->7->0(删除),如此循环直到最后一个数被删除。
 */
public class deleteNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            if (num > 1000) num = 999;
            ListNode head = new ListNode(0);
            ListNode cur = head;
            for (int i = 1; i < num; i++) {
                ListNode node = new ListNode(i);
                head.next = node;
                head = node;
            }
            head.next = cur;
            while (cur.next != cur) {
                head = cur.next.next;
                cur.next.next = head.next;
                cur = head.next;
                head = null;
            }
            System.out.println(cur.data);
        }
        sc.close();
    }
}

class ListNode {
    public int data;

    public ListNode next;

    public ListNode (int x) {
        data = x;
    }
}
