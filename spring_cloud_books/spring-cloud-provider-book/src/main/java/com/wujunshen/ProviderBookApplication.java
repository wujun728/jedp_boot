package com.wujunshen;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.wujunshen.dao")
@EnableDiscoveryClient
public class ProviderBookApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProviderBookApplication.class);

    public static void main(String[] args) {
        LOGGER.info("start execute ProviderBookApplication....\n");
        SpringApplication.run(ProviderBookApplication.class, args);
        LOGGER.info("end execute ProviderBookApplication....\n");
    }
}