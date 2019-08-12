package com.concurrent.resume;

/**
 * @author riemann
 * @date 2019/08/04 0:28
 */
public class AlternateSuspendResume extends Object implements Runnable {

    private volatile int firstVal;
    private volatile int secondVal;
    //增加标志位，用来实现线程的挂起和恢复
    private volatile boolean suspended;

    public boolean valuesAreEqual() {
        return (firstVal == secondVal);
    }

    @Override
    public void run() {
        suspended = false;
        firstVal = 0;
        secondVal = 0;
        workMethod();
    }

    private void workMethod() {
        int val = 1;
        while (true) {
            //仅当线程挂起时，才运行这行代码
            waitWhileSuspended();

            stepOne(val);
            stepTwo(val);
            val++;

            //仅当线程挂起时，才运行这行代码
            waitWhileSuspended();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void stepOne(int newVal) {
        firstVal = newVal;
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stepTwo(int newVal) {
        secondVal = newVal;
    }

    public void suspendRequest() {
        suspended = true;
    }

    public void resumeRequest() {
        suspended = false;
    }

    private void waitWhileSuspended() {
        //这是一个“繁忙等待”技术的示例。
        //它是非等待条件改变的最佳途径，因为它会不断请求处理器周期地执行检查，
        //更佳的技术是：使用Java的内置“通知-等待”机制
        while (suspended) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AlternateSuspendResume asr = new AlternateSuspendResume();
        Thread thread = new Thread(asr);
        thread.start();
        //休眠1秒，让其他线程有机会获得执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            // asr.suspendRequest();
            //让线程有机会注意到挂起请求
            //注意：这里休眠时间一定要大于
            //stepOne操作对firstVal赋值后的休眠时间，即300ms，
            //目的是为了防止在执行asr.valuesAreEqual（）进行比较时,
            //恰逢stepOne操作执行完，而stepTwo操作还没执行
            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("asr.valuesAreEqual() = " + asr.valuesAreEqual());
            asr.resumeRequest();
            //线程随机休眠0~2秒
            try {
                Thread.sleep((long) (Math.random() * 2000.0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);//退出应用程序
    }

}
