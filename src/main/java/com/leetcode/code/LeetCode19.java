package com.leetcode.code;

import com.algorithm.list.oneWayLinkedList.LinkedList;
import com.leetcode.source.ListNode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 */
public class LeetCode19 {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
        ListNode head = new ListNode(0);
        for (int i = 1; i <= 5; i++) {
            head = linkedList.add(i);
        }
        ListNode listNode = removeNthFromEnd2(head, 2);
        linkedList.print(listNode);
    }

    // 本人自己的想法
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        if (n < 1 || n > len) {
            System.out.println("Postition of node to delete is invalid.The valid inputs are 1 to "
                    + len);
            return head;
        }
        if (len == n) {
            ListNode temp = head.next;
            head = null;
            return temp;
        } else {
            len = len - n;
            ListNode prev = head;
            int count = 1;
            while (count < len) {
                prev = prev.next;
                count++;
            }
            ListNode removeNode = prev.next;
            prev.next = removeNode.next;
            removeNode = null;
        }
        return head;
    }

    // 双指针
    public static ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        for (int i = 1; i <= n + 1 ; i++) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
