package com.jyl.util.thread.impl;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.StringUtils;
import com.jyl.util.exception.GeneralException;
import com.jyl.util.thread.ThreadPoolService;

/**
 * 
 * @author Long
 *
 */
class ThreadPool {
	
	/**
	 * 线程池名称
	 */
	private String poolName = null;
	
	private int corePoolSize = -1;
	private int maximumPoolSize = -1;
	@SuppressWarnings("unused")
	private int keepAliveTime = -1;
	@SuppressWarnings("unused")
	private int queueSize = -1;
	
	Logger log = Logger.getLogger(ThreadPool.class);
	
	/**
	 * 线程池执行服务
	 */
	private ExecutorService executorService = null;
	
	/**
	 * 任务队列
	 */
	private LinkedBlockingQueue<Runnable> queueTasks = null;
	
	protected ThreadPool(String poolName, int corePoolSize, int maximumPoolSize, int keepAliveTime, int queueSize){
		init(poolName, corePoolSize, maximumPoolSize, keepAliveTime, queueSize);
	}
	
	protected ThreadPool(String poolName){
		init(poolName, ThreadPoolService.DEFAULT_CORE_POOL_SIZE, 
				ThreadPoolService.DEFAULT_MAXIMUN_POOL_SIZE, 
				ThreadPoolService.DEFAULT_KEEP_ALIVETIME, 
				ThreadPoolService.DEFAULT_QUEUE_SIZE);
	}
	
	private void init(String poolName, int corePoolSize, int maximumPoolSize, int keepAliveTime, int queueSize){
		if(StringUtils.isEmpty(poolName)){
			log.error("poolName is null or empty.");
			throw new GeneralException("POOLNAME_IS_NULL", "线程池名称不能为空。");
		}
		
		this.poolName = poolName;
		this.corePoolSize =corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.keepAliveTime = keepAliveTime;
		this.queueSize = queueSize;
		
		this.queueTasks = new LinkedBlockingQueue<Runnable>(queueSize);
		this.executorService = new  
				ThreadPoolExecutor(corePoolSize, 
						maximumPoolSize, 
						keepAliveTime, 
						TimeUnit.SECONDS,
						queueTasks, 
						new ThreadPoolExecutor.CallerRunsPolicy());
	}
	
	/**
	 * 执行任务
	 */
	protected void execute(Runnable command){
		executorService.execute(command);
	}
	
	protected Future<?> submit(Runnable task){
		return executorService.submit(task);
	}
	
	protected void print2Log(){
		StringBuilder buf = new StringBuilder().append("ThreadPool:").append(this.poolName).append(",")
				.append("corePoolSize:").append(this.corePoolSize).append(",")
				.append("maximumPoolSize:").append(this.maximumPoolSize).append(",")
				.append("tasks:").append(queueTasks.size());
		log.info(buf);
	}
	
	protected int getQueueSize(){
		return queueTasks.size();
	}
	
	/**
	 * 启动一次顺序关闭，执行以前提交的任务，但不接受新任务
	 */
	protected void shutdown(){
		executorService.shutdown();
	}
	
	/**
	 * 试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。
	 * @return
	 */
	protected List<Runnable> shutdownNow(){
		return executorService.shutdownNow();
	}
}
