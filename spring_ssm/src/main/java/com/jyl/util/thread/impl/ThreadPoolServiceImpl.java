package com.jyl.util.thread.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.jyl.util.exception.GeneralException;
import com.jyl.util.thread.ThreadPoolService;

/**
 * 
 * @author Long
 *
 */
public class ThreadPoolServiceImpl implements ThreadPoolService{
	
	private Map<String, ThreadPool> container = new HashMap<String, ThreadPool>();	
	
	private Object[] mutex = new Object[0];
	
	Logger log = Logger.getLogger(ThreadPoolServiceImpl.class);
	
	/**
	 * 执行任务
	 */
	public void execute(String poolName, Runnable command){
		synchronized(mutex){
			ThreadPool pool = container.get(poolName);
			
			if(null == pool){
				log.debug("ThreadPool (" + poolName + ") 不存在.");
				return;
			}
			
			pool.execute(command);
		}
	}
	
	/**
	 * 执行任务,同步等操作需要Future
	 */
	public Future<?> submit(String poolName, Runnable task){
		synchronized(mutex){
			ThreadPool pool = container.get(poolName);
			
			if(null == pool){
				log.debug("ThreadPool (" + poolName + ") 不存在.");
				return null;
			}
			
			return pool.submit(task);
		}
	}
	
	/**
	 * 创建线程池,常驻线程数等默认
	 */	
	public void createThreadPool(String poolName){
		if(checkName(poolName)){
			synchronized(mutex){
				ThreadPool pool = new ThreadPool(poolName);
				container.put(poolName, pool);
			}
		}
	}
	
	public void createThreadPool(String poolName, int corePoolSize, 
			int maximumPoolSize, int keepAliveTime, int queueSize){
		if(checkName(poolName)){
			synchronized(mutex){
				ThreadPool pool = new ThreadPool(poolName, corePoolSize
						, maximumPoolSize, keepAliveTime, queueSize);
				container.put(poolName, pool);
			}
		}
	}
	
	private boolean checkName(String poolName){
		if(StringUtils.isEmpty(poolName)){
			log.debug("poolName is empty.");
			return false;
		}
		if(container.containsKey(poolName)){
			log.debug(poolName + " already created.");
			return false;
		}
		return true;
	}
	
	/**
	 * 打印当前线程池队列情况
	 */
	public void print2Log(){
		synchronized(mutex){
			for(Entry<String, ThreadPool> entry : container.entrySet()){
				ThreadPool pool = entry.getValue();
				pool.print2Log();
			}
		}
	}
	
	/**
	 * 关闭线程池
	 * 返回没有执行的任务,正在执行的通过Thread.
	 */
	public List<Runnable> shutdownNow(String poolName){
		synchronized(mutex){
			ThreadPool pool = container.remove(poolName);
			
			if(null == pool){
				log.debug("ThreadPool (" + poolName + ") 不存在.");
				return null;
			}
			
			return pool.shutdownNow();
		}
	}
	
	/**
	 * 关闭线程池
	 * 已经在队列内的仍会执行完，不接收新的任务
	 */
	public void shutdown(String poolName){
		synchronized(mutex){
			ThreadPool pool = container.remove(poolName);
			
			if(null == pool){
				log.debug("ThreadPool (" + poolName + ") 不存在.");
				return ;
			}
			
			pool.shutdown();
		}
	}
	
	public void shutdownAll(){
		synchronized(mutex){
			for(Entry<String, ThreadPool> entry : container.entrySet()){
				ThreadPool pool = entry.getValue();
				pool.shutdown();
			}
			container.clear();
		}
	}
	
	public void shutdownAllNow(){
		synchronized(mutex){
			for(Entry<String, ThreadPool> entry : container.entrySet()){
				ThreadPool pool = entry.getValue();
				
				List<Runnable> tasks = pool.shutdownNow();
				if(tasks != null && tasks.size() > 0){
					tasks.clear();
				}
			}
			container.clear();
		}
	}
	
	/**
	 * 返回对应线程池队列长度
	 */
	public int getQueueSize(String poolName){
		synchronized(mutex){
			ThreadPool pool = container.get(poolName);
			
			if(null == pool){
				log.debug("ThreadPool (" + poolName + ") 不存在.");
				throw new GeneralException("THREADPOOL_NOT_FOUND", String.format("线程池(%s)不存在", poolName));
			}
			
			return pool.getQueueSize();
		}
	}
}
