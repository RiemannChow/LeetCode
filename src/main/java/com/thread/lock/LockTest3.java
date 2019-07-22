package com.thread.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author riemann
 * @date 2019/07/18 22:57
 */
public class LockTest3 {

    private Lock lock = new ReentrantLock();

    public void tryLockParamTest(Thread thread) throws InterruptedException {
        // 尝试获取锁 获取不到锁，就等3秒，如果3秒后还是获取不到就返回false
        if (lock.tryLock(3000, TimeUnit.MILLISECONDS)) {
            try {
                System.out.println("线程"+thread.getName() + "获取当前锁"); //打印当前锁的名称
                Thread.sleep(4000);//为看出执行效果，是线程此处休眠4秒
            } catch (Exception e) {
                System.out.println("线程"+thread.getName() + "发生了异常释放锁");
            }finally {
                System.out.println("线程"+thread.getName() + "执行完毕释放锁");
                lock.unlock(); //释放锁
            }
        }else{
            System.out.println("我是线程"+Thread.currentThread().getName()+"当前锁被别人占用,等待3s后仍无法获取,放弃!");
        }
    }

    public static void main(String[] args) {

        LockTest3 lockTest3 = new LockTest3();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest3.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread1");
        //声明一个线程 “线程二”
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    lockTest3.tryLockParamTest(Thread.currentThread());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "thread2");
        // 启动2个线程
        thread1.start();
        thread2.start();
    }

}
