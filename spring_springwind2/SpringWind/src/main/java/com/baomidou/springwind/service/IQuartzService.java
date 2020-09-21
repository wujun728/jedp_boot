package com.baomidou.springwind.service;

import java.text.ParseException;

import org.quartz.SchedulerException;

/**
 *
 * User 表数据服务层接口
 *
 */
public interface IQuartzService  {

	/**
	 * 添加Demo Job
	 *
	 */
	boolean addDemoJob(String jobName) throws ParseException, SchedulerException;

}