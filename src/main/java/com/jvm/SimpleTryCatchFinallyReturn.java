package com.jvm;

public class SimpleTryCatchFinallyReturn {
    public static String tryCatchFinallyReturn() {
        try {
            testNPE();
            return "success";
        } catch (Exception e) {
            System.out.println("error");
            return "error";
        } finally {
            System.out.println("finally");
            return "finally";
        }
    }

    public static void testNPE() {
    }
}
