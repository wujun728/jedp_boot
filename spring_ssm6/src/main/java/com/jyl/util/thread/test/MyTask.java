/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.thread.test;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月12日 上午11:21:47
 */
public class MyTask implements Runnable {
	
    private int taskNum;
     
    public MyTask(int num) {
        this.taskNum = num;
    }
     
	@SuppressWarnings("static-access")
	@Override
    public void run() {
        System.out.println("正在执行task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }

}
