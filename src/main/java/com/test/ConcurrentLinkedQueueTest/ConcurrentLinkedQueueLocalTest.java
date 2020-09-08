package com.test.ConcurrentLinkedQueueTest;

public class ConcurrentLinkedQueueLocalTest {
    public static void main(String[] args) {
        ConcurrentLinkedQueueLocal queue = new ConcurrentLinkedQueueLocal();
        queue.offer(1);
        queue.offer(2);
    }
}
