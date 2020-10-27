/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.util.thread.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Long, E-mail:jyl0401@163.com
 * @date 2016年7月12日 上午11:21:07
 */
public class TestCommon {

	public static void main(String[] args) {   
		
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(8));
         
        for(int i=0;i<18;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
            executor.getQueue().size()+"，已执行玩别的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
