/*
 * @(#)QuartzManager.java 2014-10-13 上午11:04:46
 * Copyright 2014 鲍建明, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.qbt.framework.quartz;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import com.qbt.framework.quartz.model.DynamicJob;
import com.qbt.framework.quartz.model.SimpleScheduleInfo;

/**
 * <p>File：QuartzManager.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-10-13 上午11:04:46</p>
 * <p>Company: 8637.com</p>
 * @author 鲍建明
 * @version 1.0
 */
public class QuartzManager
{
	
	protected static Logger logger = LogManager.getLogger(QuartzManager.class);
	
	private Scheduler scheduler;
	
	public void setScheduler(Scheduler scheduler)
	{
		this.scheduler = scheduler;
	}

	/**
	 * 开始一个任务
	 * @param dynamicJob
	 * @return
	 */
	public boolean startJob(DynamicJob dynamicJob){
		return startJob(dynamicJob, null);
	}
	
	
	/**
	 * 开始任务(新增/更新)
	 * @param dynamicJob   自定义的job 
	 * @param paramsMap   传递给job执行的数据 
	 * @param isStateFull   是否是一个同步定时任务，true：同步，false：异步 
	 * @return
	 */
	public  boolean startJob(DynamicJob dynamicJob, SimpleScheduleInfo info){
		return startJob(dynamicJob, dynamicJob.getParamsMap(), info);
	}
	
	
	
	/**
	 * 开始任务(新增/更新)
	 * @param dynamicJob   自定义的job 
	 * @param paramsMap   传递给job执行的数据 
	 * @param isStateFull   是否是一个同步定时任务，true：同步，false：异步 
	 * @return
	 */
	public  boolean startJob(DynamicJob dynamicJob, JobDataMap paramsMap, SimpleScheduleInfo info){
		if(dynamicJob == null){
			return Boolean.FALSE;
		}
		try{
			Trigger trigger = getCronTrigger(dynamicJob);
			if(null == trigger){    // 如果不存在该trigger则创建一个  
				logger.debug("QuartzManager----->新建一个任务" );
				JobDetail jobDetail = createJobDetail(dynamicJob, paramsMap);
				trigger = createCronTrigger(dynamicJob, info); 
				Date date = getScheduler().scheduleJob(jobDetail, trigger);
				logger.debug("QuartzManager----->startJob()--->date:" + date);
			}else {					// Trigger已存在，那么更新相应的定时设置  
				logger.debug("QuartzManager----->更新一个任务" );
				TriggerKey key = trigger.getKey();
				if(info == null){
					CronTrigger mCronTrigger = (CronTrigger) trigger;
					trigger = mCronTrigger.getTriggerBuilder().withSchedule(createCronSchedule(dynamicJob.getCronExpression())).build();
				} else {
					SimpleTrigger mSimpleTrigger = (SimpleTrigger) trigger;
					trigger = mSimpleTrigger.getTriggerBuilder().withSchedule(createSimpleSchedule(info)).build();
				}			
				getScheduler().rescheduleJob(key, trigger);
			}
		}
		catch (SchedulerException e){
			logger.error( "添加一个新任务失败", e);
			return Boolean.FALSE;
		}	
		return Boolean.TRUE;
	}
	
	/**
	 * 删除一个任务
	 * @param triggerName
	 * @param jobGroup
	 * @return
	 */
	public boolean deleteJob(String triggerName, String jobGroup){
		try{
			getScheduler().deleteJob(new JobKey(triggerName, jobGroup));
		}
		catch (SchedulerException e){
			logger.error( "删除一个任务失败", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 暂停一个任务
	 * @param triggerName
	 * @param jobGroup
	 * @return
	 */
	public boolean stopJob(String triggerName, String jobGroup){
		try{
			getScheduler().pauseJob(new JobKey(triggerName, jobGroup));
		}
		catch (SchedulerException e){
			logger.error( "暂停一个任务失败", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 暂停所有的任务
	 * @return
	 */
	public boolean stopJobAll(){
		try{
			getScheduler().pauseAll();
		}
		catch (SchedulerException e){
			logger.error( "暂停所有任务失败", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	
	/**
	 * 恢复一个任务
	 * @param triggerName
	 * @param jobGroup
	 * @return
	 */
	public boolean resumeJob(String triggerName, String jobGroup){
		try{
			getScheduler().resumeJob(new JobKey(triggerName, jobGroup));
		}
		catch (SchedulerException e){
			logger.error( "恢复一个任务失败", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * 恢复所有的任务
	 * @return
	 */
	public boolean resumeJobAll(){
		try{
			getScheduler().resumeAll();
		}
		catch (SchedulerException e){
			logger.error( "恢复所有任务失败", e);
			return Boolean.FALSE;
		}
		return Boolean .TRUE;
	}
	
	/**
	 * 得到job的详细信息 
	 * @param jobId
	 * @param jobGroup
	 * @return
	 */
	public JobDetail getJobDetail(String jobId, String jobGroup){
		try{
			return getScheduler().getJobDetail(new JobKey(jobId, jobGroup));
		}
		catch (SchedulerException e){
			logger.error( "获取一个任务信息失败", e);
			return null;
		}
	}
	
	/**
	 * 获取指定的触发器
	 * @param triggerName
	 * @param jobGroup
	 * @return
	 */
	public Trigger getTrigger(String triggerName, String jobGroup){
		try{
			return getScheduler().getTrigger(new TriggerKey(triggerName, jobGroup));
		}
		catch (SchedulerException e){
			logger.error( "获取一个任务触发器信息失败", e);
			return null;
		}
	}
	
	
	
	/****************************************************** private *****************************************************************/
	
	/**
	 * 创建Job的实例
	 * @param dynamicJob
	 * @param paramsMap
	 * @param isStateFull
	 * @return
	 */
	private JobDetail createJobDetail(DynamicJob dynamicJob, JobDataMap paramsMap){
		 return JobBuilder.newJob(dynamicJob.getNewJob()).withIdentity(dynamicJob.getJobId(), dynamicJob.getJobGroup()).setJobData(paramsMap).build();
	}
	
	/**
	 *  创建Trigger
	 * @param dynamicJob
	 * @return
	 */
	private Trigger createCronTrigger(DynamicJob dynamicJob, SimpleScheduleInfo info){
		ScheduleBuilder<?> mScheduleBuilder = null;
		 if(info == null){
			 logger.debug("QuartzManager----->任务类型为：--CronScheduleBuilder" );
			 mScheduleBuilder = createCronSchedule(dynamicJob.getCronExpression());
		 } else {
			 logger.debug("QuartzManager----->任务类型为：--SimpleScheduleBuilder" );
			 mScheduleBuilder =  createSimpleSchedule(info);
		 }
		 return TriggerBuilder.newTrigger().withIdentity(dynamicJob.getTriggerName(), dynamicJob.getJobGroup()).startNow().withSchedule(mScheduleBuilder).build();
	}
	
	/**
	 * 可重写
	 * @param info
	 * @return
	 */
	protected SimpleScheduleBuilder createSimpleSchedule(SimpleScheduleInfo info){
		SimpleScheduleBuilder mSimpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(info.getRepeatInterval());
		if(info.getRepeatCount() != 0){
			mSimpleScheduleBuilder.withRepeatCount(info.getRepeatCount());
		}
		mSimpleScheduleBuilder.repeatForever();
		return mSimpleScheduleBuilder;
	}
	
	
	/**
	 * 创建表达式 可重写
	 * @param cronExpression
	 * @return
	 */
	protected CronScheduleBuilder createCronSchedule(String cronExpression){
		return CronScheduleBuilder.cronSchedule(cronExpression);
	}
	
	/**
	 * 获取Scheduler
	 * @return
	 */
	public Scheduler getScheduler(){
		return this.scheduler;
	}
	
	/**
	 * 获取CronTrigger
	 * @param dynamicJob
	 * @return
	 * @throws SchedulerException
	 */
	private Trigger getCronTrigger(DynamicJob dynamicJob) throws SchedulerException{
		return getScheduler().getTrigger(new TriggerKey(dynamicJob.getTriggerName(), dynamicJob.getJobGroup()));
	}
	
}
