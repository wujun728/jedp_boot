/*
 * @(#)DynamicJob.java 2014-10-13 上午11:02:01
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qbt.framework.quartz.model;

import java.io.Serializable;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobDataMap;



/**
 * <p>File：DynamicJob.java</p>
 * <p>Title: </p>
 * <p>Description:
 * 1.同步的执行类，需要从StatefulMethodInvokingJob继承
 * 2.异步的执行类，需要从MethodInvokingJob继承
 * 请使用 {@link com.qbt.framework.quartz.model.DynamicJob.Builer}  来构建任务类
 * </p>
 * <p>Copyright: Copyright (c) 2014 2014-10-13 上午11:02:01</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class DynamicJob implements Serializable
{

	private static final long serialVersionUID = -557308458567257412L;
	
	private String		jobId;						// 任务的Id，一般为所定义Bean的ID
	private String		jobName;					// 任务的描述
	private String		jobGroup;					// 任务所属组的名称
	private Integer			jobStatus;					// 任务的状态，0：启用；1：禁用；2：已删除
	private String		cronExpression;			// 定时任务运行时间表达式
	private String		description;						// 任务描述
	private Class<? extends Job> newJob;				//任务类
	
	private JobDataMap paramsMap;					//附加参数
	
	
	/**
	 * <p>Class: Builer.java</p>
	 * <p>Description: 构建类</p>
	 * <pre>
	 *         
	 * </pre>
	 * @author 鲍建明
	 * @date 2015-11-17 下午2:02:08
	 * @version 1.0.0
	 */
	public static class Builer{
		private DynamicJob job;
		private JobDataMap paramsMap;
		
		public static Builer create(){
			return new Builer();
		}
		
		Builer(){
			this(null, null);
		}
		
		Builer(String jobId, String jobGroup){
			job = new DynamicJob();
			job.jobId = jobId;
			job.jobGroup = jobGroup;
			paramsMap = new JobDataMap();
		}
		
		public static Builer create(String jobId, String jobGroup){
			return new Builer(jobId, jobGroup);
		}
		
		public Builer putAll(Map<? extends String, ? extends Object> t){
			paramsMap.putAll(t);
			return this;
		}
		
		
		/**
	     * <p>
	     * Adds the given <code>int</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, int value) {
	    	paramsMap.put(key, value);
	        return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>long</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, long value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>float</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, float value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>double</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, double value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>boolean</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, boolean value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>char</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, char value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>String</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, String value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }

	    /**
	     * <p>
	     * Adds the given <code>Object</code> value to the <code>StringKeyDirtyFlagMap</code>.
	     * </p>
	     */
	    public Builer put(String key, Object value) {
	    	paramsMap.put(key, value);
	    	return this;
	    }
	    
		/**
		 * 设置一个job Key
		 * <pre></pre>
		 * @param jobId
		 * @param jobGroup
		 * @return
		 */
		public Builer setKey(String jobId, String jobGroup){
			job.jobId = jobId;
			job.jobGroup = jobGroup;
			return this;
		}
		
		/**
		 * 设置jobName
		 * <pre></pre>
		 * @param jobName
		 * @return
		 */
		public Builer setName(String jobName){
			job.jobName = jobName;
			return this;
		}
		
		/**
		 * 设置任务状态 
		 * <pre></pre>
		 * @param jobStatus  任务的状态，0：启用；1：禁用；2：已删除
		 * @return
		 */
		public Builer setStatus(Integer jobStatus){
			job.jobStatus = jobStatus;
			return this;
		}
		
		/**
		 * 添加一个新的定时任务
		 * <pre></pre>
		 * @param clazz
		 * @param cronExpression
		 * @return
		 */
		public Builer newJob(Class<? extends Job> clazz, String cronExpression){
			job.newJob = clazz;
			job.cronExpression = cronExpression;
			return this;
		}
		
		/**
		 * 设置描述 
		 * <pre></pre>
		 * @param description
		 * @return
		 */
		public Builer setDescription(String description){
			job.description = description;
			return this;
		}
		
		/**
		 * 构建
		 * <pre></pre>
		 * @return
		 */
		public DynamicJob build(){
			if( job.jobId == null || job.jobGroup == null){
				throw new NullPointerException("jobId or jobGroup is not null");
			}
			if( job.newJob == null || job.cronExpression == null){
				throw new NullPointerException("newJob or cronExpression is not null");
			}
			job.paramsMap = paramsMap;
			return this.job;
		}
		
		
	}
	
	
	   /** 
     * 得到该job的Trigger名字 
     * @return 
     */  
    public String getTriggerName() {  
        return this.getJobId() + "Trigger";  
    }  
    
	public String getJobId()
	{
		return jobId;
	}
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}
	public String getJobName()
	{
		return jobName;
	}
	public void setJobName(String jobName)
	{
		this.jobName = jobName;
	}
	public String getJobGroup()
	{
		return jobGroup;
	}
	public void setJobGroup(String jobGroup)
	{
		this.jobGroup = jobGroup;
	}
	public Integer getJobStatus()
	{
		return jobStatus;
	}
	public void setJobStatus(Integer jobStatus)
	{
		this.jobStatus = jobStatus;
	}
	public String getCronExpression()
	{
		return cronExpression;
	}
	public void setCronExpression(String cronExpression)
	{
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Class<? extends Job> getNewJob() {
		return newJob;
	}

	public void setNewJob(Class<? extends Job> newJob) {
		this.newJob = newJob;
	}

	public JobDataMap getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(JobDataMap paramsMap) {
		this.paramsMap = paramsMap;
	}
	
	
}
