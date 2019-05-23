package com.concurrent;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author riemann
 * @date 2019/05/24 0:46
 */
public class CopyOnWriteArrayListTest {
    private static volatile CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws InterruptedException {

        arrayList.add("hello");
        arrayList.add("world");
        arrayList.add("hi");
        arrayList.add("riemann");

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                //修改list中下标为1的元素为java
                arrayList.set(1, "java");
                //删除元素
                arrayList.remove(2);
            }
        });
        //保证在修改线程启动前获取迭代器
        Iterator<String> iterator = arrayList.iterator();
        //启动线程
        threadOne.start();
        //等子线程执行完毕
        threadOne.join();
        //迭代元素
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
