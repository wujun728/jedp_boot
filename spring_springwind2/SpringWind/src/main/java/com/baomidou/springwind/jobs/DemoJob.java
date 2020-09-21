package com.baomidou.springwind.jobs;

import com.baomidou.framework.quartz.QuartzJobSupport;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.quartz.JobExecutionContext;


/**
 * <p>
 * Quartz Demo Job
 * </p>
 * <p>
 * 继承 QuartzJobSupport
 * </p>
 */
public class DemoJob extends QuartzJobSupport {
    private static final Log LOGGER = LogFactory.getLog(DemoJob.class);

    @Override
    public void innerIter(JobExecutionContext jobExecutionContext) {
        LOGGER.debug("------------- 测试任务 -------------");
    }
}
