package com.algorithm.tenSortingAlgorithm;

import java.util.*;

public class BucketSort {

    static final int DEFAULT_INITIAL_CAPACITY = 10; // 默认 10，这里具体看你的数据的量

    private static void bucketSort(int[] arr) {
        // 新建一个桶的集合
        ArrayList<LinkedList<Integer>> buckets = new ArrayList<LinkedList<Integer>>();
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            // 新建一个桶，并将其添加到桶的集合中去。
            // 由于桶内元素会频繁的插入，所以选择 LinkedList 作为桶的数据结构
            buckets.add(new LinkedList<Integer>());
        }
        // 将输入数据全部放入桶中并完成排序
        for (Integer data : arr) {
            int index = getBucketIndex(data);
            insertSort(buckets.get(index), data);
        }
        // 将桶中元素全部取出来并放入 arr 中输出
        int index = 0;
        for (LinkedList<Integer> bucket : buckets) {
            for (Integer data : bucket) {
                arr[index++] = data;
            }
        }
    }

    /**
     * 我们选择插入排序作为桶内元素排序的方法 每当有一个新元素到来时，我们都调用该方法将其插入到恰当的位置
     * @param bucket
     * @param data
     */
    private static void insertSort(List<Integer> bucket, Integer data) {
        ListIterator<Integer> iterator = bucket.listIterator();
        boolean insertFlag = true;
        while (iterator.hasNext()) {
            if (data <= iterator.next()) {
                iterator.previous(); // 把迭代器的位置偏移回上一个位置
                iterator.add(data); // 把数据插入到迭代器的当前位置
                insertFlag = false;
                break;
            }
        }
        if (insertFlag) {
            bucket.add(data); // 否则把数据插入到链表末端
        }
    }

    /**
     * 计算得到输入元素应该放到哪个桶内
     * @param data
     * @return
     */
    private static int getBucketIndex(Integer data) {
        // 这里例子写的比较简单，仅使用对10取整作为其桶的索引值
        // 实际开发中需要根据场景具体设计
        return data / DEFAULT_INITIAL_CAPACITY;
    }

    public static void main(String[] args) {
        int[] arr = {1,28,29,3,21,11,7,6,18};
        bucketSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
