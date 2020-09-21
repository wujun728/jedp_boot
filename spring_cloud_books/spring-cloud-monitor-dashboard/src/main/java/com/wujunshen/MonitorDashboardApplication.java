package com.wujunshen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 测试步骤:
 * 1. 访问http://localhost:8030/hystrix.stream 可以查看Dashboard
 * 2. 在上面的输入框填入: http://想监控的服务:端口/hystrix.stream进行测试
 * 注意：首先要先调用一下想监控的服务的API，否则将会显示一个空的图表.
 */
@SpringBootApplication
@EnableHystrixDashboard
public class MonitorDashboardApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(MonitorDashboardApplication.class);

    public static void main(String[] args) {
        LOGGER.info("start execute MonitorDashboardApplication....\n");
        //SpringApplication.run(MonitorDashboardApplication.class, args);
        new SpringApplicationBuilder(MonitorDashboardApplication.class).web(true).run(args);
        LOGGER.info("end execute MonitorDashboardApplication....\n");
    }
}
