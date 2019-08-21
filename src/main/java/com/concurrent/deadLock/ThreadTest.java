package com.concurrent.deadLock;

/**
 * @author riemann
 * @date 2019/08/14 22:44
 */
public class ThreadTest {

    public static void main(String[] args) {
        /*new Thread(new Thread1()).start();
        new Thread(new Thread2()).start();*/
        new Thread(new Thread3()).start();
    }

}

class ThreadResource {
    public static Object resource1 = new Object();
    public static Object resource2 = new Object();
}

class Thread1 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread1 is running");
            synchronized (ThreadResource.resource1) {
                System.out.println("Thread1 lock resource1");
                Thread.sleep(2000);//休眠2s等待线程2锁定资源2
                synchronized (ThreadResource.resource2) {
                    System.out.println("Thread1 lock resource2");
                }
                System.out.println("Thread1 release resource2");
            }
            System.out.println("Thread1 release resource1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Thread1 is stop");
    }
}

class Thread2 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread2 is running");
            synchronized (ThreadResource.resource2) {
                System.out.println("Thread2 lock resource2");
                Thread.sleep(2000);//休眠2s等待线程1锁定资源1
                synchronized (ThreadResource.resource1) {
                    System.out.println("Thread2 lock resource1");
                }
                System.out.println("Thread2 release resource1");
            }
            System.out.println("Thread2 release resource2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Thread2 is stop");
    }
}

class Thread3 implements Runnable {

    @Override
    public void run() {
        try {
            System.out.println("Thread is running");
            if (ThreadResource.resource1.hashCode() > ThreadResource.resource2.hashCode()) {
                //先锁定resource1
                synchronized (ThreadResource.resource1) {
                    System.out.println("Thread lock resource1");
                    Thread.sleep(2000);
                    synchronized (ThreadResource.resource2)
                    {
                        System.out.println("Thread lock resource2");
                    }
                    System.out.println("Thread release resource2");
                }
                System.out.println("Thread release resource1");
            } else {
                //先锁定resource2
                synchronized (ThreadResource.resource2)
                {
                    System.out.println("Thread lock resource2");
                    Thread.sleep(2000);
                    synchronized (ThreadResource.resource1)
                    {
                        System.out.println("Thread lock resource1");
                    }
                    System.out.println("Thread release resource1");
                }
                System.out.println("Thread release resource2");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Thread1 is stop");
    }
}