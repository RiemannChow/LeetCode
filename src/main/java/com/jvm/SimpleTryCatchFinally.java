package com.jvm;

public class SimpleTryCatchFinally {
    private int tryBlock;
    private int catchBlock;
    private int finallyBlock;
    private int methodExit;

    public void test() {
        try {
            tryBlock = 0;
        } catch (Exception e) {
            catchBlock = 1;
            throw new IllegalArgumentException();
        } finally {
            finallyBlock = 2;
        }
        methodExit = 3;
    }
}
