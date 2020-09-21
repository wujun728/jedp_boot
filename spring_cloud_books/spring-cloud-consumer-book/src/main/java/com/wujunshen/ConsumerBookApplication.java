package com.wujunshen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class ConsumerBookApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerBookApplication.class);

    public static void main(String[] args) {
        LOGGER.info("start execute ConsumerBookApplication....\n");
        SpringApplication.run(ConsumerBookApplication.class, args);
        LOGGER.info("end execute ConsumerBookApplication....\n");
    }
}