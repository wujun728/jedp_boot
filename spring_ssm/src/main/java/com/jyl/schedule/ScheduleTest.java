/**
 * Copyright (C) 2016 Juno Inc., All Rights Reserved.
 */
package com.jyl.schedule;

import org.springframework.stereotype.Component;

/**
 *
 * @author Wujun
 * @date 2016年7月28日 下午9:15:21
 */
@Component
public class ScheduleTest {

    //@Scheduled(cron="${schedule.test}")
    public void exejob() {
        System.out.println(System.currentTimeMillis()+"-------do work!");
    }
}
