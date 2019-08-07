package com.concurrent.syn;

/**
 * @author riemann
 * @date 2019/08/02 0:10
 */
public class PrintLetter_Syn {

    public static class ThreadPrinter implements Runnable {

        private String name;
        private Object prev;
        private Object self;
        private ThreadPrinter(String name, Object prev, Object self) {
            this.name = name;
            this.prev = prev;
            this.self = self;
        }

        @Override
        public void run() {
            int count = 10;
            while (count > 0) {// 多线程并发，不能用if，必须使用whil循环
                synchronized (prev) {// 先获取 prev 锁
                    synchronized (self) {// 再获取 self 锁
                        System.out.print(name);
                        count--;
                        self.notifyAll();// 唤醒其他线程竞争self锁，注意此时self锁并未立即释放。
                    }
                    // 此时执行完self的同步块，这时self锁才释放。
                    try {
                        prev.wait();// 立即释放 prev锁，当前线程休眠，等待唤醒
                        // JVM会在wait()对象锁的线程中随机选取一线程，赋予其对象锁，唤醒线程，继续执行。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws Exception {
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        ThreadPrinter pa = new ThreadPrinter("a", c, a);
        ThreadPrinter pb = new ThreadPrinter("b", a, b);
        ThreadPrinter pc = new ThreadPrinter("c", b, c);

        new Thread(pa).start();
        Thread.sleep(10);//保证初始ABC的启动顺序
        new Thread(pb).start();
        Thread.sleep(10);
        new Thread(pc).start();
        Thread.sleep(10);
    }

}
