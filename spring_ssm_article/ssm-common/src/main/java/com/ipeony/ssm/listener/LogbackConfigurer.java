/**
 * CCC 2013-2013 All Rights Reserved.
 */
package com.ipeony.ssm.listener;

import java.io.FileNotFoundException;
import java.net.URL;

import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

/**
 * 
 * 功能描述： 为SpringFramework配置logback
 * 
 * @author 作者 13071472
 * @version 1.0.0
 * @date 2013年11月6日 下午4:43:10
 */

public class LogbackConfigurer {
    private static Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

    public static void initLogging(String location) {
        String resolvedLocation = SystemPropertyUtils.resolvePlaceholders(location);
        URL url = null;
        try {
            url = ResourceUtils.getURL(resolvedLocation);
        } catch (FileNotFoundException e) {
            logger.error("logback配置文件不存在");
        }
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        loggerContext.reset();
        JoranConfigurator joranConfigurator = new JoranConfigurator();
        joranConfigurator.setContext(loggerContext);
        try {
            joranConfigurator.doConfigure(url);
        } catch (JoranException e) {
            logger.error("logback配置文件加载失败");
        }
        logger.info("成功加载logback配置文件");
    }
}
