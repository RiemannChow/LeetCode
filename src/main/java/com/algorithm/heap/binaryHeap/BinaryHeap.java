package com.algorithm.heap.binaryHeap;

import java.util.Random;

/**
 * @author riemann
 * @date 2019/09/04 0:09
 */
public class BinaryHeap {

    private int elementCount;

    private int[] elements;

    private static final int DEFAULT_CAPACITY = 16;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int[] data) {
        this();
        for (int d : data) {
            push(d);
        }
    }

    public BinaryHeap(int capacity) {
        elements = new int[tableSizeFor(capacity)];
    }

    /**
     * 入队
     * @param data 入队数据
     */
    public void push(int data) {
        if (elements.length - 1 == elementCount) {
            resize((elementCount + 1) << 1);
        }
        elements[++elementCount] = data;
        floatElement(elementCount);
    }

    /**
     * 出队
     * @return 返回队列最大或者最小值
     */
    public int pop() {
        if (elements.length < elementCount >> 2) {
            resize(elementCount >> 2);
        }
        int res = elements[1];
        swap(1, elementCount);
        elements[elementCount--] = 0;
        sinkElement();
        return res;
    }

    /**
     * 查看队首元素
     * @return 返回队列第一个元素
     */
    public int peek() {
        return elementCount == 0 ? -1 : elements[1];
    }

    /**
     * 元素个数
     * @return 返回元素个数
     */
    public int size() {
        return elementCount;
    }

    /**
     * 检验非空
     * @return TRUE 队列为空
     */
    public boolean isEmpty() {
        return elementCount == 0;
    }

    /**
     * 重新设置elements元素大小
     * @param size 新数组的大小
     */
    private void resize(int size) {
        if (size > MAXIMUM_CAPACITY) {
            // TODO
        }
        int[] newElements = new int[size];
        copyArray(elements, newElements);
        elements = newElements;
    }

    /**
     * 元素上浮
     * @param index 上浮元素索引位置
     */
    private void floatElement(int index) {
        while (index > 1 && elements[index >> 1] < elements[index]) {
            swap(index >> 1, index);
            index >>= 1;
        }
    }

    /**
     * 元素下沉
     */
    private void sinkElement() {
        int index = 1;
        while (index << 1 <= elementCount &&
                (elements[index] < elements[index << 1] || elements[index] < elements[(index << 1) + 1])) {
            int p = index << 1;
            int i = elements[p] > elements[p + 1] ? p : p + 1;
            swap(index, i);
            index = (i & 0x01) == 1 ? (index << 1) + 1 : index << 1;
        }
    }

    /**
     * HashMap中的方法，把传入参数变为下一个 1 << n (2的n次方大于 cap 并且 2的n-1次方小于 cap)
     * @param cap   改变的参数
     * @return      1 << n
     */
    private static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /**
     * 数组复制
     * @param source    被复制的源数组
     * @param target    复制目标数组
     */
    private static void copyArray(int[] source, int[] target) {
        System.arraycopy(source, 0, target, 0, source.length);
    }

    /**
     * 交换数组两元素位置
     * @param indexA    元素A的索引位置
     * @param indexB    元素B的索引位置
     */
    private void swap(int indexA, int indexB) {
        int temp = elements[indexA];
        elements[indexA] = elements[indexB];
        elements[indexB] = temp;
    }

    public static void main(String[] args) {
        int length = 20;
        Random random = new Random();
        int[] elements = new int[length];
        for (int i = 0; i < length; i++) {
            int ele = random.nextInt(100);
            elements[i] = ele;
            System.out.print(ele + " ");
        }
        BinaryHeap maxHeap = new BinaryHeap(elements);
        System.out.println();
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.pop() + " ");
        }
    }

}
