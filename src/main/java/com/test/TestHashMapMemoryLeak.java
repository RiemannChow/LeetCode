package com.test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * @author riemann
 * @date 2019/04/21 0:03
 */
public class TestHashMapMemoryLeak {
    public static void main(String[] args) {
        Map<Key, String> map = new HashMap<Key, String>(1000);
        //Map<String, String> map = new HashMap<String, String>(1000);  key为String还有Key的方法里重写equals和hashcode方法
        int count = 0;
        while (true) {
            map.put(new Key("dummyKey"), "value");
            //map.put("key", "value");  解决内存泄露
            count++;
            if (count % 1000 == 0) {
                System.out.println("map size: " + map.size());
                System.out.println("Free memory after count " + count + " is " + getFreeMemory() + "MB");
                sleep(100);
            }
        }
    }

    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

    static class Key {
        private String key;
        public Key(String key) {
            this.key = key;
        }
        /*public boolean equals(Object obj) {
            if (obj instanceof Key) {
                return key.equals(((Key) obj).key);
            } else {
                return false;
            }
        }
        public int hashCode() {
            return key.hashCode();
        }*/
    }
}
