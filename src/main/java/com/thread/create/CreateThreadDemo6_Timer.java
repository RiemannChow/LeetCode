package com.thread.create;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务
 *
 * @author riemann
 * @date 2019/07/13 17:01
 */
public class CreateThreadDemo6_Timer {

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args) throws Exception {
        // 创建定时器
        Timer timer = new Timer();

        // 提交计划任务
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行了...");
            }
        }, new Date(),1000);
    }

}
