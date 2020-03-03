package com.algorithm.list.doublyLinkedList;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    public static class DoublyLinkedList {
        DoublyLinkedList prev, next;
        Integer key, value;
    }

    DoublyLinkedList head, tail;
    Integer capacity;
    Map<Integer, DoublyLinkedList> map = new HashMap<Integer, DoublyLinkedList>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new DoublyLinkedList();
        tail = new DoublyLinkedList();

        head.next = tail;
        tail.prev = head;
    }

    private void put(int key, int value) {
        DoublyLinkedList node = map.get(key);
        if (node == null) {
            capacity--;
            node = new DoublyLinkedList();
            node.key = key;
            node.value = value;

            map.put(key, node);
            moveToHead(node);

            if (capacity < 0) {
                DoublyLinkedList temp = removeTailnode();
                map.remove(temp.key);
                capacity++;
            }
        } else {
            node.value = value;
            map.put(key, node);
            moveToHead(node);
        }
    }

    private Integer get(int key) {
        DoublyLinkedList node = map.get(key);
        remove(node);
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DoublyLinkedList node) {
        node.prev = head;
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
    }

    private DoublyLinkedList removeTailnode() {
        DoublyLinkedList temp = tail.prev;
        remove(temp);
        return temp;
    }

    private void remove(DoublyLinkedList temp) {
        DoublyLinkedList p = temp.prev;
        DoublyLinkedList q = temp.next;
        p.next = q;
        q.prev = p;
    }

}
