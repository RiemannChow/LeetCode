package com.jvm;

public class SimpleTryCatch {
    public static void main(String[] args) {
        try {
            testNPE();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testNPE() {
    }
}
