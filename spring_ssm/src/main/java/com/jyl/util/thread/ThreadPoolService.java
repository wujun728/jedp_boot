package com.jyl.util.thread;

import java.util.List;
import java.util.concurrent.Future;

/**
 * 
 * @author Long
 *
 */
public interface ThreadPoolService {	
	/**
	 * 默认核心池大小,没有任何执行任务时驻守线程数
	 */
	public static final int DEFAULT_CORE_POOL_SIZE = 3;
	/**
	 * 线程池最大线程数,任务多时,能启动最大的线程数
	 */
	public static final int DEFAULT_MAXIMUN_POOL_SIZE = 10;
	/**
	 * 线程空闲时间,过了这个时间没有任务,线程将关闭,单位秒
	 */
	public static final int DEFAULT_KEEP_ALIVETIME = 120;
	/**
	 * 默认任务队列长度,非执行任务将在队列中等待执行
	 */
	public static final int DEFAULT_QUEUE_SIZE = 500;
	
	/**
	 * 创建线程池,常驻线程数等默认,名称不能为空及重复会抛出异常
	 */
	public void createThreadPool(String poolName);
	
	public void createThreadPool(String poolName, int corePoolSize, int maximumPoolSize, int keepAliveTime, int queueSize);
	
	/**
	 * 具体意义参看JDK ThreadPoolExecutor
	 */
	public void execute(String poolName, Runnable command);
	/**
	 * 具体意义参看JDK ThreadPoolExecutor
	 */
	public Future<?> submit(String poolName, Runnable task);
	
	/**
	 * 关闭线程池
	 */
	//返回没有执行的任务,正在执行的通过Thread.
	public List<Runnable> shutdownNow(String poolName);
	//已经在队列内的仍会执行完，不接收新的任务
	public void shutdown(String poolName);
	public void shutdownAll();
	public void shutdownAllNow();
	/**
	 * 返回对应线程池队列长度
	 */
	public int getQueueSize(String poolName);
	/**
	 * 打印当前线程池队列情况
	 */
	public void print2Log();
}