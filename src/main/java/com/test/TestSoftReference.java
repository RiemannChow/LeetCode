//package com.test;
//
//import java.lang.ref.SoftReference;
//
//public class TestSoftReference {
//    public static void main(String[] args) {
//        System.out.println("start...");
//        Object object = new Object();
//        SoftReference<Object> sr = new SoftReference<Object>(object);
//        object = null;
//        if (sr != null) {
//            object = sr.get();
//            if (jvm.OutOfMemory()) { //jvm内存不足时
//                object = null; // 转换为软引用
//                System.gc(); // 垃圾回收器进行回收
//            }
//        } else {
//            object = new Object();
//            sr = new SoftReference<Object>(object);
//            System.out.println(sr); //输出对象 java.lang.ref.SoftReference@511d50c0
//        }
//
//        System.out.println(object); //输出null
//        System.out.println("end...");
//    }
//}
//
//class Object {
//    int[] object;
//    public Object() {
//        object = new int[100000000];
//    }
//}
