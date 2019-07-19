package com.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author riemann
 * @date 2019/07/18 22:49
 */
public class LockTest2 {

    private Lock lock = new ReentrantLock();

    // 尝试获取锁 tryLock() 它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），则返回false
    public void tryLockTest(Thread thread) {
        if (lock.tryLock()) { //尝试获取锁
            try {
                System.out.println("线程"+thread.getName() + "获取当前锁"); //打印当前锁的名称
                Thread.sleep(2000);//为看出执行效果，是线程此处休眠2秒
            } catch (Exception e) {
                System.out.println("线程"+thread.getName() + "发生了异常释放锁");
            }finally {
                System.out.println("线程"+thread.getName() + "执行完毕释放锁");
                lock.unlock(); //释放锁
            }
        } else {
            System.out.println("我是线程"+Thread.currentThread().getName()+"当前锁被别人占用，我无法获取!");
        }
    }

    public static void main(String[] args) {

        LockTest2 lockTest2 = new LockTest2();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest2.tryLockTest(Thread.currentThread());
            }
        }, "thread1");
        //声明一个线程 “线程二”
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest2.tryLockTest(Thread.currentThread());
            }
        }, "thread2");
        // 启动2个线程
        thread1.start();
        thread2.start();
    }

}
